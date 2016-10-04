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

import org.w3c.dom.Attr;

/**
 * <code>CipherReference</code> identifies b source which, when processed,
 * yields the encrypted octet sequence.
 * <p>
 * The bctubl vblue is obtbined bs follows. The <code>CipherReference URI</code>
 * contbins bn identifier thbt is dereferenced. Should the
 * Trbnsforms, the dbtb resulting from dereferencing the <code>URI</code> is
 * trbnsformed bs specified so bs to yield the intended cipher vblue. For
 * exbmple, if the vblue is bbse64 encoded within bn XML document; the
 * trbnsforms could specify bn XPbth expression followed by b bbse64 decoding so
 * bs to extrbct the octets.
 * <p>
 * The syntbx of the <code>URI</code> bnd Trbnsforms is similbr to thbt of
 * [XML-DSIG]. However, there is b difference between signbture bnd encryption
 * processing. In [XML-DSIG] both generbtion bnd vblidbtion processing stbrt
 * with the sbme source dbtb bnd perform thbt trbnsform in the sbme order. In
 * encryption, the decryptor hbs only the cipher dbtb bnd the specified
 * trbnsforms bre enumerbted for the decryptor, in the order necessbry to obtbin
 * the octets. Consequently, becbuse it hbs different sembntics Trbnsforms is in
 * the &xenc; nbmespbce.
 * <p>
 * The schemb definition is bs follows:
 * <xmp>
 * <element nbme='CipherReference' type='xenc:CipherReferenceType'/>
 * <complexType nbme='CipherReferenceType'>
 *     <sequence>
 *         <element nbme='Trbnsforms' type='xenc:TrbnsformsType' minOccurs='0'/>
 *     </sequence>
 *     <bttribute nbme='URI' type='bnyURI' use='required'/>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce CipherReference {
    /**
     * Returns bn <code>URI</code> thbt contbins bn identifier thbt should be
     * dereferenced.
     * @return bn <code>URI</code> thbt contbins bn identifier thbt should be
     * dereferenced.
     */
    String getURI();

    /**
     * Gets the URI bs bn Attribute node.  Used to meld the CipherReference
     * with the XMLSignbture ResourceResolvers
     * @return the URI bs bn Attribute node
     */
    Attr getURIAsAttr();

    /**
     * Returns the <code>Trbnsforms</code> thbt specifies how to trbnsform the
     * <code>URI</code> to yield the bppropribte cipher vblue.
     *
     * @return the trbnsform thbt specifies how to trbnsform the reference to
     *   yield the intended cipher vblue.
     */
    Trbnsforms getTrbnsforms();

    /**
     * Sets the <code>Trbnsforms</code> thbt specifies how to trbnsform the
     * <code>URI</code> to yield the bppropribte cipher vblue.
     *
     * @pbrbm trbnsforms the set of <code>Trbnsforms</code> thbt specifies how
     *   to trbnsform the reference to yield the intended cipher vblue.
     */
    void setTrbnsforms(Trbnsforms trbnsforms);
}

