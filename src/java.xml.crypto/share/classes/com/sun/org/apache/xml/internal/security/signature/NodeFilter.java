/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.signbture;

import org.w3c.dom.Node;

/**
 * An interfbce to tell to the c14n if b node is included or not in the output
 */
public interfbce NodeFilter {

    /**
     * Tells if b node must be output in c14n.
     * @pbrbm n
     * @return 1 if the node should be output.
     *         0 if node must not be output,
     *         -1 if the node bnd bll it's child must not be output.
     *
     */
    int isNodeInclude(Node n);

    /**
     * Tells if b node must be output in b c14n.
     * The cbller must bssured thbt this method is blwbys cbll
     * in document order. The implementbtions cbn use this
     * restriction to optimize the trbnsformbtion.
     * @pbrbm n
     * @pbrbm level the relbtive level in the tree
     * @return 1 if the node should be output.
     *         0 if node must not be output,
     *         -1 if the node bnd bll it's child must not be output.
     */
    int isNodeIncludeDO(Node n, int level);

}
