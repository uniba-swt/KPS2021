export interface MemoryGraph {
  nodes: Node[];
  edges: Edge[];
  groups?: Group[];
}

export interface Node {
  id: string;
  address: string;
  type: string;
  value: string;
  size: number;
  artificial?: boolean;
  meta?: { [key: string]: string };
}

export interface Edge {
  src: string;
  dst: string;
  op: string;
  artificial?: boolean;
}

export interface Group {
  id: string;
  children: string[];
  origin: "shape" | "type";
  meta?: GroupMeta;
}

export interface GroupMeta {
  predicateName?: string;
  structName?: string;
  predicateArgs?: string[];
}

export interface Predicate {
  name: string;
  args: string[];
  consumed: string[];
}

export interface CellNode {
  id: string;
}

export interface CellEdge {
  srcID: string;
  dstID: string;
}

export interface CellGraph {
  matrix: { [nodeID: string]: { [edgeID: string]: string } };
  entries: string[];
  consumed?: string[];
}
