const predicates: { [key: string]: string[] } = {
  sll: ["next"],
  csll: ["next"],
  dll: ["fwd", "bwd"],
  cdll: ["next", "previous"],
  bt: ["left", "right"],
};

const config = {
  predicates: predicates,
  writeProgramToDisk: true,
  null: "null",
};
export default config;
