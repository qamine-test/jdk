/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge com.sun.jmx.snmp;

/**
 * This interfbce bllows you to compute key locblizbtion bnd deltb generbtion. It is useful when bdding user in USM MIB. An instbnce of <CODE> SnmpUsmKeyHbndler </CODE> is bssocibted to ebch <CODE> SnmpEngine </CODE> object.
 * When computing key, bn buthenticbtion blgorithm is needed. The supported ones bre : usmHMACMD5AuthProtocol bnd usmHMACSHAAuthProtocol.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public interfbce SnmpUsmKeyHbndler {

    /**
     * DES privbcy blgorithm key size. To be used when locblizing privbcy key
     */
    public stbtic int DES_KEY_SIZE = 16;

    /**
     * DES privbcy blgorithm deltb size. To be used when cblculing privbcy key deltb.
     */
    public stbtic int DES_DELTA_SIZE = 16;

    /**
     * Trbnslbte b pbssword to b key. It MUST be complibnt to RFC 2574 description.
     * @pbrbm blgoNbme The buthenticbtion blgorithm to use.
     * @pbrbm pbssword Pbssword to convert.
     * @return The key.
     * @exception IllegblArgumentException If the blgorithm is unknown.
     */
    public byte[] pbssword_to_key(String blgoNbme, String pbssword) throws IllegblArgumentException;
    /**
     * Locblize the pbssed key using the pbssed <CODE>SnmpEngineId</CODE>. It MUST be complibnt to RFC 2574 description.
     * @pbrbm blgoNbme The buthenticbtion blgorithm to use.
     * @pbrbm key The key to locblize;
     * @pbrbm engineId The Id used to locblize the key.
     * @return The locblized key.
     * @exception IllegblArgumentException If the blgorithm is unknown.
     */
    public byte[] locblizeAuthKey(String blgoNbme, byte[] key, SnmpEngineId engineId) throws IllegblArgumentException;

    /**
     * Locblize the pbssed privbcy key using the pbssed <CODE>SnmpEngineId</CODE>. It MUST be complibnt to RFC 2574 description.
     * @pbrbm blgoNbme The buthenticbtion blgorithm to use.
     * @pbrbm key The key to locblize;
     * @pbrbm engineId The Id used to locblize the key.
     * @pbrbm keysize The privbcy blgorithm key size.
     * @return The locblized key.
     * @exception IllegblArgumentException If the blgorithm is unknown.
     */
    public byte[] locblizePrivKey(String blgoNbme, byte[] key, SnmpEngineId engineId,int keysize) throws IllegblArgumentException;

    /**
     * Cblculbte the deltb pbrbmeter needed when processing key chbnge. This computbtion is done by the key chbnge initibtor. It MUST be complibnt to RFC 2574 description.
     * @pbrbm blgoNbme The buthenticbtion blgorithm to use.
     * @pbrbm oldKey The old key.
     * @pbrbm newKey The new key.
     * @pbrbm rbndom The rbndom vblue.
     * @return The deltb.
     * @exception IllegblArgumentException If the blgorithm is unknown.
     */
    public byte[] cblculbteAuthDeltb(String blgoNbme, byte[] oldKey, byte[] newKey, byte[] rbndom) throws IllegblArgumentException;

    /**
     * Cblculbte the deltb pbrbmeter needed when processing key chbnge for b privbcy blgorithm. This computbtion is done by the key chbnge initibtor. It MUST be complibnt to RFC 2574 description.
     * @pbrbm blgoNbme The buthenticbtion blgorithm to use.
     * @pbrbm oldKey The old key.
     * @pbrbm newKey The new key.
     * @pbrbm rbndom The rbndom vblue.
     * @pbrbm deltbSize The blgo deltb size.
     * @return The deltb.
     * @exception IllegblArgumentException If the blgorithm is unknown.
     */
    public byte[] cblculbtePrivDeltb(String blgoNbme, byte[] oldKey, byte[] newKey, byte[] rbndom, int deltbSize) throws IllegblArgumentException;

}
