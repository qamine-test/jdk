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
import org.w3c.dom.Element;

/**
 * A wrbpper for b pointer from b key vblue of bn <code>EncryptedKey</code> to
 * items encrypted by thbt key vblue (<code>EncryptedDbtb</code> or
 * <code>EncryptedKey</code> elements).
 * <p>
 * It is defined bs follows:
 * <xmp>
 * <complexType nbme='ReferenceType'>
 *     <sequence>
 *         <bny nbmespbce='##other' minOccurs='0' mbxOccurs='unbounded'/>
 *     </sequence>
 *     <bttribute nbme='URI' type='bnyURI' use='required'/>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 * @see ReferenceList
 */
public interfbce Reference {
    /**
     * Returns the <code>Element</code> tbg nbme for this <code>Reference</code>.
     *
     * @return the tbg nbme of this <code>Reference</code>.
     */
    String getType();

    /**
     * Returns b <code>URI</code> thbt points to bn <code>Element</code> thbt
     * were encrypted using the key defined in the enclosing
     * <code>EncryptedKey</code> element.
     *
     * @return bn Uniform Resource Identifier thbt qublifies bn
     *   <code>EncryptedType</code>.
     */
    String getURI();

    /**
     * Sets b <code>URI</code> thbt points to bn <code>Element</code> thbt
     * were encrypted using the key defined in the enclosing
     * <code>EncryptedKey</code> element.
     *
     * @pbrbm uri the Uniform Resource Identifier thbt qublifies bn
     *   <code>EncryptedType</code>.
     */
    void setURI(String uri);

    /**
     * Returns bn <code>Iterbtor</code> over bll the child elements contbined in
     * this <code>Reference</code> thbt will bid the recipient in retrieving the
     * <code>EncryptedKey</code> bnd/or <code>EncryptedDbtb</code> elements.
     * These could include informbtion such bs XPbth trbnsforms, decompression
     * trbnsforms, or informbtion on how to retrieve the elements from b
     * document storbge fbcility.
     *
     * @return child elements.
     */
    Iterbtor<Element> getElementRetrievblInformbtion();

    /**
     * Adds retrievbl informbtion.
     *
     * @pbrbm info
     */
    void bddElementRetrievblInformbtion(Element info);

    /**
     * Removes the specified retrievbl informbtion.
     *
     * @pbrbm info
     */
    void removeElementRetrievblInformbtion(Element info);
}
