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

/**
 * A contbiner for <code>ds:Trbnsform</code>s.
 * <p>
 * It is defined bs follows:
 * <xmp>
 * <complexType nbme='TrbnsformsType'>
 *     <sequence>
 *         <element ref='ds:Trbnsform' mbxOccurs='unbounded'/>
 *     </sequence>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 * @see com.sun.org.bpbche.xml.internbl.security.encryption.CipherReference
 */
public interfbce Trbnsforms {
    /**
     * Temporbry method to turn the XMLEncryption Trbnsforms clbss
     * into b DS clbss.  The mbin logic is currently implemented in the
     * DS clbss, so we need to get to get the bbse clbss.
     * <p>
     * <b>Note</b> This will be removed in future versions
     */
    com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms getDSTrbnsforms();

}
