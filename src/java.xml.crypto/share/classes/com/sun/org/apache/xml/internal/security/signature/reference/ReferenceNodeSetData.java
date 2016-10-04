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
/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id$
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.signbture.reference;

import jbvb.util.Iterbtor;

import org.w3c.dom.Node;

/**
 * An bbstrbct representbtion of b <code>ReferenceDbtb</code> type contbining b node-set.
 */
public interfbce ReferenceNodeSetDbtb extends ReferenceDbtb {

    /**
     * Returns b rebd-only iterbtor over the nodes contbined in this
     * <code>NodeSetDbtb</code> in
     * <b href="http://www.w3.org/TR/1999/REC-xpbth-19991116#dt-document-order">
     * document order</b>. Attempts to modify the returned iterbtor
     * vib the <code>remove</code> method throw
     * <code>UnsupportedOperbtionException</code>.
     *
     * @return bn <code>Iterbtor</code> over the nodes in this
     *    <code>NodeSetDbtb</code> in document order
     */
    Iterbtor<Node> iterbtor();

}
