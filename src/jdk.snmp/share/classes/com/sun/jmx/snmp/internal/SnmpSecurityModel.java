/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp.internbl;

import com.sun.jmx.snmp.SnmpSecurityException;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpSecurityPbrbmeters;

/**
 * Security model interfbce. Any security model implementbtion must implement this interfbce in order to be integrbted in the engine frbmework. Security models bre cblled when SNMP messbges bre received or sent. They debl with security (buthenticbtion bnd privbcy).
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public interfbce SnmpSecurityModel extends SnmpModel {
    /**
     * Cblled when b request is to be sent to the network. It must be securized.
     * <BR>The specified pbrbmeters bre defined in RFC 2572 (see blso the {@link com.sun.jmx.snmp.SnmpV3Messbge} clbss).
     * @pbrbm cbche The cbche thbt hbs been crebted by cblling <CODE>crebteSecurityCbche</CODE> on this model.
     * @pbrbm version The SNMP protocol version.
     * @pbrbm msgID The current request id.
     * @pbrbm msgMbxSize The messbge mbx size.
     * @pbrbm msgFlbgs The messbge flbgs (reportbble, Auth bnd Priv).
     * @pbrbm msgSecurityModel This current security model.
     * @pbrbm pbrbms The security pbrbmeters thbt contbin the model dependbnt pbrbmeters.
     * @pbrbm contextEngineID The context engine ID.
     * @pbrbm contextNbme The context nbme.
     * @pbrbm dbtb The mbrshblled vbrbind list.
     * @pbrbm dbtbLength The mbrshblled vbrbind list length.
     * @pbrbm outputBytes The buffer to fill with securized request. This is b representbtion independbnt mbrshblled formbt. This buffer will be sent to the network.
     * @return The mbrshblled byte number.
     */
    public int generbteRequestMsg(SnmpSecurityCbche cbche,
                                  int version,
                                  int msgID,
                                  int msgMbxSize,
                                  byte msgFlbgs,
                                  int msgSecurityModel,
                                  SnmpSecurityPbrbmeters pbrbms,
                                  byte[] contextEngineID,
                                  byte[] contextNbme,
                                  byte[] dbtb,
                                  int dbtbLength,
                                  byte[] outputBytes)
        throws SnmpTooBigException, SnmpStbtusException,
               SnmpSecurityException;

    /**
     * Cblled when b response is to be sent to the network. It must be securized.
     * <BR>The specified pbrbmeters bre defined in RFC 2572 (see blso the {@link com.sun.jmx.snmp.SnmpV3Messbge} clbss).
     * @pbrbm cbche The cbche thbt hbs been crebted by cblling <CODE>crebteSecurityCbche</CODE> on this model.
     * @pbrbm version The SNMP protocol version.
     * @pbrbm msgID The current request id.
     * @pbrbm msgMbxSize The messbge mbx size.
     * @pbrbm msgFlbgs The messbge flbgs (reportbble, Auth bnd Priv)
     * @pbrbm msgSecurityModel This current security model.
     * @pbrbm pbrbms The security pbrbmeters thbt contbin the model dependbnt pbrbmeters.
     * @pbrbm contextEngineID The context engine ID.
     * @pbrbm contextNbme The context nbme.
     * @pbrbm dbtb The mbrshblled vbrbind list.
     * @pbrbm dbtbLength The mbrshblled vbrbind list length.
     * @pbrbm outputBytes The buffer to fill with securized request. This is b representbtion independbnt mbrshblled formbt. This buffer will be sent to the network.
     * @return The mbrshblled byte number.
     */
    public int generbteResponseMsg(SnmpSecurityCbche cbche,
                                   int version,
                                   int msgID,
                                   int msgMbxSize,
                                   byte msgFlbgs,
                                   int msgSecurityModel,
                                   SnmpSecurityPbrbmeters pbrbms,
                                   byte[] contextEngineID,
                                   byte[] contextNbme,
                                   byte[] dbtb,
                                   int dbtbLength,
                                   byte[] outputBytes)
        throws SnmpTooBigException, SnmpStbtusException,
               SnmpSecurityException;
    /**
     * Cblled when b request is received from the network. It hbndles buthenticbtion bnd privbcy.
     * <BR>The specified pbrbmeters bre defined in RFC 2572 (see blso the {@link com.sun.jmx.snmp.SnmpV3Messbge} clbss).
     * @pbrbm cbche The cbche thbt hbs been crebted by cblling <CODE>crebteSecurityCbche</CODE> on this model.
     * @pbrbm version The SNMP protocol version.
     * @pbrbm msgID The current request id.
     * @pbrbm msgMbxSize The messbge mbx size.
     * @pbrbm msgFlbgs The messbge flbgs (reportbble, Auth bnd Priv)
     * @pbrbm msgSecurityModel This current security model.
     * @pbrbm pbrbms The security pbrbmeters in b mbrshblled formbt. The informbtions contbined in this brrby bre model dependbnt.
     * @pbrbm contextEngineID The context engine ID or null if encrypted.
     * @pbrbm contextNbme The context nbme or null if encrypted.
     * @pbrbm dbtb The mbrshblled vbrbind list or null if encrypted
     * @pbrbm encryptedPdu The encrypted pdu or null if not encrypted.
     * @pbrbm decryptedPdu The decrypted pdu. If no decryption is to be done, the pbssed context engine ID, context nbme bnd dbtb could be used to fill this object.
     * @return The decoded security pbrbmeters.

     */
    public SnmpSecurityPbrbmeters
        processIncomingRequest(SnmpSecurityCbche cbche,
                               int version,
                               int msgID,
                               int msgMbxSize,
                               byte msgFlbgs,
                               int msgSecurityModel,
                               byte[] pbrbms,
                               byte[] contextEngineID,
                               byte[] contextNbme,
                               byte[] dbtb,
                               byte[] encryptedPdu,
                               SnmpDecryptedPdu decryptedPdu)
        throws SnmpStbtusException, SnmpSecurityException;
 /**
     * Cblled when b response is received from the network. It hbndles buthenticbtion bnd privbcy.
     * <BR>The specified pbrbmeters bre defined in RFC 2572 (see blso the {@link com.sun.jmx.snmp.SnmpV3Messbge} clbss).
     * @pbrbm cbche The cbche thbt hbs been crebted by cblling <CODE>crebteSecurityCbche</CODE> on this model.
     * @pbrbm version The SNMP protocol version.
     * @pbrbm msgID The current request id.
     * @pbrbm msgMbxSize The messbge mbx size.
     * @pbrbm msgFlbgs The messbge flbgs (reportbble, Auth bnd Priv)
     * @pbrbm msgSecurityModel This current security model.
     * @pbrbm pbrbms The security pbrbmeters in b mbrshblled formbt. The informbtions cointbined in this brrby bre model dependbnt.
     * @pbrbm contextEngineID The context engine ID or null if encrypted.
     * @pbrbm contextNbme The context nbme or null if encrypted.
     * @pbrbm dbtb The mbrshblled vbrbind list or null if encrypted
     * @pbrbm encryptedPdu The encrypted pdu or null if not encrypted.
     * @pbrbm decryptedPdu The decrypted pdu. If no decryption is to be done, the pbssed context engine ID, context nbme bnd dbtb could be used to fill this object.
     * @return The security pbrbmeters.

     */
    public SnmpSecurityPbrbmeters processIncomingResponse(SnmpSecurityCbche cbche,
                                                          int version,
                                                          int msgID,
                                                          int msgMbxSize,
                                                          byte msgFlbgs,
                                                          int msgSecurityModel,
                                                          byte[] pbrbms,
                                                          byte[] contextEngineID,
                                                          byte[] contextNbme,
                                                          byte[] dbtb,
                                                          byte[] encryptedPdu,
                                                          SnmpDecryptedPdu decryptedPdu)
        throws SnmpStbtusException, SnmpSecurityException;

    /**
     * Instbntibte bn <CODE>SnmpSecurityCbche</CODE> thbt is dependbnt to the model implementbtion.
     * @return The model dependbnt security cbche.
     */
    public SnmpSecurityCbche crebteSecurityCbche();
    /**
     * Relebse the previously crebted cbche.
     * @pbrbm cbche The security cbche to relebse.
     */
    public void relebseSecurityCbche(SnmpSecurityCbche cbche);
}
