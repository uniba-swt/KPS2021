
# Single mutations

This folder contains the TypeScript source code of the single mutation prototype tool. Test inputs are encoded as graphs in JSON format, see `CellGraph` in `types.ts`. Data structure specification are encoded as Prolog predicates, see `src/predicates.pl`. Prolog queries are executed using [Tau-Prolog](http://tau-prolog.org), see `src/prologExecutor.ts`. The graph isomorphism check and the single mutation operator are defined in `src/mutations.ts`. The generation of exhaustive graphs is defined in `src/exhaustive.ts`. The script to obtain the data for the KPS 2021 evaluation benchmark is defined in `src/KPS2021.ts`.

## Usage

1. Build the docker image via `docker-compose build`.
2. Start the docker container via `docker-compose run code`. This directory is mapped to `/data`.
3. Install node dependencies via `npm install`.
4. Run `npm run KPS2021` to generate the negative test inputs using the exhaustive and single mutation approach. Test inputs are written to folder `evaluation-KPS` as JSON files and serve as inputs for the subsequent evaluation.


## Notes

- Run `npm run test` to run all unit tests and `npm run testCase foo` to run unit tests matching the description `foo`.
- Enable `writeProgramToDisk` in `config.ts` and use http://tau-prolog.org/sandbox/ to debug the generated Prolog program (see `debug.pl`) and its query (see comment in last line of `debug.pl`).
- Run `npm run start` to execute `index.ts`.
- Run `npm run watch` to automatically re-execute `index.ts` on file change.
- Run `npm run autoformat` to format the code of all ts files in the project.
- Use `npx cloc src` to count the lines of code in the src folder.
