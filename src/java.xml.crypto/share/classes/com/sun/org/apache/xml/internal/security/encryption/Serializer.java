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
pbckbge com.sun.org.bpbche.xml.internbl.security.encryption;

import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Converts <code>String</code>s into <code>Node</code>s bnd visb versb.
 */
public interfbce Seriblizer {

    /**
     * Set the Cbnonicblizer object to use.
     */
    void setCbnonicblizer(Cbnonicblizer cbnon);

    /**
     * Returns b <code>byte[]</code> representbtion of the specified
     * <code>Element</code>.
     *
     * @pbrbm element the <code>Element</code> to seriblize.
     * @return the <code>byte[]</code> representbtion of the serilbized
     *   <code>Element</code>.
     * @throws Exception
     */
    byte[] seriblizeToByteArrby(Element element) throws Exception;

    /**
     * Returns b <code>byte[]</code> representbtion of the specified
     * <code>NodeList</code>.
     *
     * @pbrbm content the <code>NodeList</code> to seriblize.
     * @return the <code>byte[]</code> representbtion of the seriblized
     *   <code>NodeList</code>.
     * @throws Exception
     */
    byte[] seriblizeToByteArrby(NodeList content) throws Exception;

    /**
     * Use the Cbnonicblizer to seriblize the node
     * @pbrbm node
     * @return the (byte[]) cbnonicblizbtion of the node
     * @throws Exception
     */
    byte[] cbnonSeriblizeToByteArrby(Node node) throws Exception;

    /**
     * @pbrbm source
     * @pbrbm ctx
     * @return the Node resulting from the pbrse of the source
     * @throws XMLEncryptionException
     */
    Node deseriblize(byte[] source, Node ctx) throws XMLEncryptionException;
}
