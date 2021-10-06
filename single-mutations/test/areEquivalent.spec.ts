import { expect } from 'chai';
import _ from 'lodash';
import config from '../src/config';
import { CellGraph, Group } from '../src/types';
import { areEquivalent} from '../src/mutations';

it('areEquivalent: same binary tree with 3 elements', () => {
    let g1: CellGraph = {
        entries: ['n1'],
        matrix: {
            'n1': { left: 'n2', 'right': 'n3' },
            'n2': { left: config.null, right: config.null },
            'n3': { left: config.null, right: config.null },
        },
        consumed: []
    }

    let g2: CellGraph = {
        entries: ['n1'],
        matrix: {
            'n1': { 'left': 'n3', right: 'n2' },
            'n2': { 'left': config.null, right: config.null },
            'n3': { 'left': config.null, right: config.null },
        },
        consumed: []
    }

    expect(true).to.equal(areEquivalent(g1, g2))
});

it('areEquivalent: 1 vs no node', () => {
    let g1: CellGraph = {
        entries: ['n1'],
        matrix: {
            'n1': { left: config.null, right: config.null },
        },
        consumed: []
    }

    let g2: CellGraph = {
        entries: [config.null],
        matrix: {
        },
        consumed: []
    }

    expect(false).to.equal(areEquivalent(g1, g2))
});

it('areEquivalent: sth != null', () => {
    let g1: CellGraph = {
        entries: ['n1'],
        matrix: {
            'n1': { left: 'n2', right: config.null },
            'n2': { left: config.null, right: 'n1' },
        },
        consumed: []
    }

    let g2: CellGraph = {
        entries: ['n1'],
        matrix: {
            'n1': { left: 'n2', right: config.null },
            'n2': { left: config.null, right: config.null },
        },
        consumed: []
    }

    expect(false).to.equal(areEquivalent(g1, g2))
});

