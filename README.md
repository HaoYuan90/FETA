# FETA

A Technical Assessment (To be deleted soon)

## Building the project

In $repo_root/feta folder, run

```bash
  mvn clean compile assembly:single
```

## Running tests

In $repo_root/feta folder, run

```bash
  mvn test
```

## Running the project

Locate the jar built from previous step in $project_root/target
In $repo_root/feta folder, run

```bash
  java -jar $jar -f $input_file -o $output_file
```

For example

```bash
  java -jar target/feta-1.0-SNAPSHOT-jar-with-dependencies.jar -f src/test/resources/test_1.csv -o out.sql
```

## Writeups

### Important notes:

- I assumed DB is PostgreSQL based on the create table command
- There are multiple units of measures for energy in documentation, I didnt bother figuring out how to convert all of
  them to Wh.
- I didnt find any mention of timezone of these meter reading files in documentation. Australia has 3 timezones. Im
  guessing the date/datetime fields are in local date/datetime. For the sake of this excercise I treated them as UTC.
- I took liberty to interpret "write a piece of code" as "produce a CLI tool to do the job while making sure there are
  reusable pieces for future adaptation".

### What is the rationale for the technologies you have decided to use?

Java with Maven as package manager is picked because Im more familiar with this stack. Libraries built here can easily
be reimplemented as lambda functions. I was initially considering solving this problem in Spark stack, imagine a spark
job where each worker reads a whole file. This approach was discarded for the following reasons:

- It reads like NEM12 data are delivered in XML messages, messages cannot be partitioned since "300" rows need context
  of
  200 rows to be processed. Imagine a spark worker reads a 300 row but the corresponding "200" row is in another
  partition.
- Amount of data can be handled by lambda architecture where each file ingested triggers a function, we do not need map
  reduce to process the raw NEM12 files.
  Napkin estimate for data size in rows: (12 million households + 12 million businesses) * 100 data points a day

### What would you have done differently if you had more time?

- [**Testing**] Instead of testing generated insert queries against a golden file, it is better to test that we can
  perform the insertions correctly with the help of a psql test container in jUnit tests.
- [**Security**] Generating insert queries using string builder runs a risk of SQL injection. For this excercise
  specifically the "nmi"
  field should have been validated or properly escaped. Using a framework with prepared statements to generate the query
  will mitigate this risk.
- [**Feature**] Currently the insertion queries are generated as one insert batch for each interval data (300) rows.
  Ideally users should be able to input a desired batch size. A better batch size is likely more than 1000 values per
  insert.
- [**Feature**] Input data can use more validation, for example, assert each type of data rows have the right number of
  columns.
- [**Performance**] This implementation checkpoints the data in memory (list of IntervalMeteringData) instead of
  operating as a streaming processor. The job can run out of heap for really large files.

### What is the rationale for the design choices that you have made?

- It is ok to have non-generic record type handlers. From documentation, it seems non header/footer record types are
  not shared between different data feed types. (For example, record type 200 is for interval data and 250 is for
  accumulation data). I therefore chose to handle different record types within the file reader.
- Chose to not use ORM to save time.
- Chose to not parse CSV into a data object, that will be a lot of boilerplate code I dont need for this task.

### Noone asked but thoughts...

The goal of "generating insert statements" is at odds with the requirement that this needs to be built with "production
grade implementation". It is difficult to see why the insert statements need to be generated as a separate step instead
of loading data into DB directly. This also puts limitation on frameworks candidates can use. At least for Java and
Python, prepared statement frameworks are built in db connectors. They require infra (connection to a DB) to use
and infra is forbidden by the assignment.

Will it be better if the assignment provides instruction to run a Postgres docker image and ask candidates to create a
CLI tool to populate data into the containerized database instead?