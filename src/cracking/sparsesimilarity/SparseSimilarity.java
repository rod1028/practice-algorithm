package cracking.sparsesimilarity;

import java.util.*;

/**
 * Sparse Similarity: The similarity of two documents (each with distinct words) is defined to be the
 * size of the intersection divided by the size of the union. For example, if the documents consist of
 * integers, the similarity of {1, 5, 3} and {1, 7, 2, 3} is e. 4, because the intersection has size
 * 2 and the union has size 5.
 * We have a long list of documents (with distinct values and each with an associated 10) where the
 * similarity is believed to be "sparse:'That is, any two arbitrarily selected documents are very likely to
 * have similarity O. Design an algorithm that returns a list of pairs of document IDs and the associated
 * similarity.
 * o.
 * Print only the pairs with similarity greater than Empty documents should not be printed at all. For
 * simplicity, you may assume each document is represented as an array of distinct integers.
 * EXAMPLE
 * Input:
 * 13:
 * 16:
 * 19:
 * 24:
 * {14, 15, lee, 9, 3}
 * {32, 1, 9, 3, 5}
 * {15, 29, 2, 6, 8, 7}
 * {7, 1e}
 * Output:
 * ID1, ID2 SIMILARITY
 * 13, 19
 * 13, 16
 * 19, 24 e.1
 * e.25
 * e.14285714285714285
 */
public class SparseSimilarity {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> docs = new HashMap<>();
        //documents to numbers
        docs.put(13, new ArrayList<>(List.of(14, 15, 100, 9, 3)));
        docs.put(16, new ArrayList<>(List.of(32, 1, 9, 3, 5)));
        docs.put(19, new ArrayList<>(List.of(15, 29, 2, 6, 8, 7)));
        docs.put(24, new ArrayList<>(List.of(7, 10)));

        //numbers  to documents
        HashMap<Integer, ArrayList<Integer>> revIndex = generateReverseIndex(docs);
        //get all pairs that have at least 1 common element
        HashMap<String, DocPair> pairs = generateValidPairs(docs, revIndex);

        //print the result
        for (Map.Entry<String, DocPair> entry : pairs.entrySet()) {
            DocPair pair = entry.getValue();
            System.out.printf("%d, %d : %f\n",pair.docA, pair.docB, pair.getSimilarity());
        }
    }

    private static HashMap<String, DocPair> generateValidPairs(HashMap<Integer, ArrayList<Integer>> docs, HashMap<Integer, ArrayList<Integer>> revIndex) {
        HashMap<String, DocPair> pairs = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : revIndex.entrySet()) {
            ArrayList<Integer> docList = entry.getValue();
            if (docList.size() < 2) {
                continue;
            }
            //for each unique pair of docs, add an inersection value/common el
            for (int i = 0; i < docList.size() - 1; i++) {
                Integer docA = docList.get(i);

                for (int j = i + 1; j < docList.size(); j++) {
                    Integer docB = docList.get(j);

                    //TODO it is a better way? unique touple in Java
                    String pairKey = docA + "_" + docB;
                    DocPair pair;
                    if (pairs.containsKey(pairKey)) {
                        pair = pairs.get(pairKey);
                    } else {
                        pair = new DocPair(docA, docB);
                        pair.union = getUnion(docs.get(docA), docs.get(docB));
                        pairs.put(pairKey, pair);
                    }
                    pair.intersection++;
                }
            }
        }
        return pairs;
    }

    private static HashMap<Integer, ArrayList<Integer>> generateReverseIndex(HashMap<Integer, ArrayList<Integer>> docs) {
        HashMap<Integer, ArrayList<Integer>> revIndex = new HashMap<>();

        for (Map.Entry<Integer, ArrayList<Integer>> entry : docs.entrySet()) {
            Integer docId = entry.getKey();
            for (int number : entry.getValue()) {
                if (revIndex.containsKey(number)) {
                    revIndex.get(number).add(docId);
                } else {
                    revIndex.put(number, new ArrayList<>() {{
                        add(docId);
                    }});
                }
            }
        }
        return revIndex;
    }

    private static int getUnion(ArrayList<Integer> a, ArrayList<Integer> b) {
        HashSet<Integer> all = new HashSet<>();
        all.addAll(a);
        all.addAll(b);

        return all.size();
    }
}


class DocPair {
    final int docA;
    final int docB;
    int intersection = 0;
    int union = 0;

    DocPair(int docA, int docB) {
        this.docA = docA;
        this.docB = docB;
    }

//     @Override
//     public int hashCode(){
//         return 3 * this.docA + 5 * this.docB;
//     }
//
//     @Override
//     public boolean equals(Object obj) {
//         if (obj instanceof DocPair == false){
//             return false;
//         }
//         if (obj == this) {
//             return true;
//         }
//         DocPair b = (DocPair)obj;
//         return (this.docA == b.docA && this.docB == b.docB) ||
//                 (this.docB == b.docA && this.docA == b.docB);
//     }

    float getSimilarity() {
        return (float) this.intersection / (float) this.union;
    }
}