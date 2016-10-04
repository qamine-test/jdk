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

import jbvb.util.Iterbtor;

/**
 * <code>ReferenceList</code> is bn element thbt contbins pointers from b key
 * vblue of bn <code>EncryptedKey</code> to items encrypted by thbt key vblue
 * (<code>EncryptedDbtb</code> or <code>EncryptedKey</code> elements).
 * <p>
 * It is defined bs follows:
 * <xmp>
 * <element nbme='ReferenceList'>
 *     <complexType>
 *         <choice minOccurs='1' mbxOccurs='unbounded'>
 *             <element nbme='DbtbReference' type='xenc:ReferenceType'/>
 *             <element nbme='KeyReference' type='xenc:ReferenceType'/>
 *         </choice>
 *     </complexType>
 * </element>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 * @see Reference
 */
public interfbce ReferenceList {

    /** DATA TAG */
    int DATA_REFERENCE = 0x00000001;

    /** KEY TAG */
    int KEY_REFERENCE  = 0x00000002;

    /**
     * Adds b reference to this reference list.
     *
     * @pbrbm reference the reference to bdd.
     * @throws IllegblAccessException if the <code>Reference</code> is not bn
     *   instbnce of <code>DbtbReference</code> or <code>KeyReference</code>.
     */
    void bdd(Reference reference);

    /**
     * Removes b reference from the <code>ReferenceList</code>.
     *
     * @pbrbm reference the reference to remove.
     */
    void remove(Reference reference);

    /**
     * Returns the size of the <code>ReferenceList</code>.
     *
     * @return the size of the <code>ReferenceList</code>.
     */
    int size();

    /**
     * Indicbtes if the <code>ReferenceList</code> is empty.
     *
     * @return <code><b>true</b></code> if the <code>ReferenceList</code> is
     *     empty, else <code><b>fblse</b></code>.
     */
    boolebn isEmpty();

    /**
     * Returns bn <code>Iterbtor</code> over bll the <code>Reference</code>s
     * contbined in this <code>ReferenceList</code>.
     *
     * @return Iterbtor.
     */
    Iterbtor<Reference> getReferences();

    /**
     * <code>DbtbReference</code> fbctory method. Returns b
     * <code>DbtbReference</code>.
     * @pbrbm uri
     * @return b <code>DbtbReference</code>.
     */
    Reference newDbtbReference(String uri);

    /**
     * <code>KeyReference</code> fbctory method. Returns b
     * <code>KeyReference</code>.
     * @pbrbm uri
     * @return b <code>KeyReference</code>.
     */
    Reference newKeyReference(String uri);
}
