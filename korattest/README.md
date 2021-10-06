# korattest

The folder `korat` contains an adapted version of the Korat tool (commit hash `d3c478df6d73c46ba4643b82bc909eb3f22b2f3a`). It allows us to write generated test inputs to disk in JSON format. Korat is available online at [https://github.com/korattest/korat](https://github.com/korattest/korat). 


## Usage

1. Enter the korat folder via `cd korat`.
2. Build the docker image via `docker-compose build`.
3. Start the docker container via `docker-compose run korat`. The korat directory is mapped to `/data`.
4. Use `ant test` to run the provided test cases. Data structure instances, which have been constructed during testing, are written to `./test-reports/`.
