/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge org.ietf.jgss;

import sun.security.jgss.spi.*;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;

/**
 * This interfbce encbpsulbtes the GSS-API security context bnd provides
 * the security services thbt bre bvbilbble over the context.  Security
 * contexts bre estbblished between peers using locblly bcquired
 * credentibls.  Multiple contexts mby exist simultbneously between b pbir
 * of peers, using the sbme or different set of credentibls.  GSS-API
 * functions in b mbnner independent of the underlying trbnsport protocol
 * bnd depends on its cblling bpplicbtion to trbnsport the tokens thbt bre
 * generbted by the security context between the peers.<p>
 *
 * If the cbller instbntibtes the context using the defbult
 * <code>GSSMbnbger</code> instbnce, then the Kerberos v5 GSS-API mechbnism
 * is gubrbnteed to be bvbilbble for context estbblishment. This mechbnism
 * is identified by the Oid "1.2.840.113554.1.2.2" bnd is defined in RFC
 * 1964.<p>
 *
 * Before the context estbblishment phbse is initibted, the context
 * initibtor mby request specific chbrbcteristics desired of the
 * estbblished context. Not bll underlying mechbnisms support bll
 * chbrbcteristics thbt b cbller might desire. After the context is
 * estbblished, the cbller cbn check the bctubl chbrbcteristics bnd services
 * offered by thbt context by mebns of vbrious query methods. When using
 * the Kerberos v5 GSS-API mechbnism offered by the defbult
 * <code>GSSMbnbger</code> instbnce, bll optionbl services will be
 * bvbilbble locblly. They bre mutubl buthenticbtion, credentibl
 * delegbtion, confidentiblity bnd integrity protection, bnd per-messbge
 * replby detection bnd sequencing. Note thbt in the GSS-API, messbge integrity
 * is b prerequisite for messbge confidentiblity.<p>
 *
 * The context estbblishment occurs in b loop where the
 * initibtor cblls {@link #initSecContext(byte[], int, int) initSecContext}
 * bnd the bcceptor cblls {@link #bcceptSecContext(byte[], int, int)
 * bcceptSecContext} until the context is estbblished. While in this loop
 * the <code>initSecContext</code> bnd <code>bcceptSecContext</code>
 * methods produce tokens thbt the bpplicbtion sends over to the peer. The
 * peer pbsses bny such token bs input to its <code>bcceptSecContext</code>
 * or <code>initSecContext</code> bs the cbse mby be.<p>
 *
 * During the context estbblishment phbse, the {@link
 * #isProtRebdy() isProtRebdy} method mby be cblled to determine if the
 * context cbn be used for the per-messbge operbtions of {@link
 * #wrbp(byte[], int, int, MessbgeProp) wrbp} bnd {@link #getMIC(byte[],
 * int, int, MessbgeProp) getMIC}.  This bllows bpplicbtions to use
 * per-messbge operbtions on contexts which bren't yet fully
 * estbblished.<p>
 *
 * After the context hbs been estbblished or the <code>isProtRebdy</code>
 * method returns <code>true</code>, the query routines cbn be invoked to
 * determine the bctubl chbrbcteristics bnd services of the estbblished
 * context.  The bpplicbtion cbn blso stbrt using the per-messbge methods
 * of {@link #wrbp(byte[], int, int, MessbgeProp) wrbp} bnd
 * {@link #getMIC(byte[], int, int, MessbgeProp) getMIC} to obtbin
 * cryptogrbphic operbtions on bpplicbtion supplied dbtb.<p>
 *
 * When the context is no longer needed, the bpplicbtion should cbll
 * {@link #dispose() dispose} to relebse bny system resources the context
 * mby be using.<p>
 *
 * A security context typicblly mbintbins sequencing bnd replby detection
 * informbtion bbout the tokens it processes. Therefore, the sequence in
 * which bny tokens bre presented to this context for processing cbn be
 * importbnt. Also note thbt none of the methods in this interfbce bre
 * synchronized. Therefore, it is not bdvisbble to shbre b
 * <code>GSSContext</code> bmong severbl threbds unless some bpplicbtion
 * level synchronizbtion is in plbce.<p>
 *
 * Finblly, different mechbnism providers might plbce different security
 * restrictions on using GSS-API contexts. These will be documented by the
 * mechbnism provider. The bpplicbtion will need to ensure thbt it hbs the
 * bppropribte permissions if such checks bre mbde in the mechbnism lbyer.<p>
 *
 * The exbmple code presented below demonstrbtes the usbge of the
 * <code>GSSContext</code> interfbce for the initibting peer.  Different
 * operbtions on the <code>GSSContext</code> object bre presented,
 * including: object instbntibtion, setting of desired flbgs, context
 * estbblishment, query of bctubl context flbgs, per-messbge operbtions on
 * bpplicbtion dbtb, bnd finblly context deletion.<p>
 *
 * <pre>
 *    // Crebte b context using defbult credentibls
 *    // bnd the implementbtion specific defbult mechbnism
 *    GSSMbnbger mbnbger ...
 *    GSSNbme tbrgetNbme ...
 *    GSSContext context = mbnbger.crebteContext(tbrgetNbme, null, null,
 *                                           GSSContext.INDEFINITE_LIFETIME);
 *
 *    // set desired context options prior to context estbblishment
 *    context.requestConf(true);
 *    context.requestMutublAuth(true);
 *    context.requestReplbyDet(true);
 *    context.requestSequenceDet(true);
 *
 *    // estbblish b context between peers
 *
 *    byte []inToken = new byte[0];
 *
 *    // Loop while there still is b token to be processed
 *
 *    while (!context.isEstbblished()) {
 *
 *        byte[] outToken
 *            = context.initSecContext(inToken, 0, inToken.length);
 *
 *        // send the output token if generbted
 *        if (outToken != null)
 *            sendToken(outToken);
 *
 *        if (!context.isEstbblished()) {
 *            inToken = rebdToken();
 *    }
 *
 *     // displby context informbtion
 *     System.out.println("Rembining lifetime in seconds = "
 *                                          + context.getLifetime());
 *     System.out.println("Context mechbnism = " + context.getMech());
 *     System.out.println("Initibtor = " + context.getSrcNbme());
 *     System.out.println("Acceptor = " + context.getTbrgNbme());
 *
 *     if (context.getConfStbte())
 *             System.out.println("Confidentiblity (i.e., privbcy) is bvbilbble");
 *
 *     if (context.getIntegStbte())
 *             System.out.println("Integrity is bvbilbble");
 *
 *     // perform wrbp on bn bpplicbtion supplied messbge, bppMsg,
 *     // using QOP = 0, bnd requesting privbcy service
 *     byte [] bppMsg ...
 *
 *     MessbgeProp mProp = new MessbgeProp(0, true);
 *
 *     byte []tok = context.wrbp(bppMsg, 0, bppMsg.length, mProp);
 *
 *     sendToken(tok);
 *
 *     // relebse the locbl-end of the context
 *     context.dispose();
 *
 * </pre>
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public interfbce GSSContext {

    /**
     * A lifetime constbnt representing the defbult context lifetime.  This
     * vblue is set to 0.
     */
    public stbtic finbl int DEFAULT_LIFETIME = 0;

    /**
     * A lifetime constbnt representing indefinite context lifetime.
     * This vblue must is set to the mbximum integer vblue in Jbvb -
     * {@link jbvb.lbng.Integer#MAX_VALUE Integer.MAX_VALUE}.
     */
    public stbtic finbl int INDEFINITE_LIFETIME = Integer.MAX_VALUE;

    /**
     * Cblled by the context initibtor to stbrt the context crebtion
     * phbse bnd process bny tokens generbted
     * by the peer's <code>bcceptSecContext</code> method.
     * This method mby return bn output token which the bpplicbtion will need
     * to send to the peer for processing by its <code>bcceptSecContext</code>
     * method. The bpplicbtion cbn cbll {@link #isEstbblished()
     * isEstbblished} to determine if the context estbblishment phbse is
     * complete on this side of the context.  A return vblue of
     * <code>fblse</code> from <code>isEstbblished</code> indicbtes thbt
     * more tokens bre expected to be supplied to
     * <code>initSecContext</code>.  Upon completion of the context
     * estbblishment, the bvbilbble context options mby be queried through
     * the get methods.<p>
     *
     * Note thbt it is possible thbt the <code>initSecContext</code> method
     * return b token for the peer, bnd <code>isEstbblished</code> return
     * <code>true</code> blso. This indicbtes thbt the token needs to be sent
     * to the peer, but the locbl end of the context is now fully
     * estbblished.<p>
     *
     * Some mechbnism providers might require thbt the cbller be grbnted
     * permission to initibte b security context. A fbiled permission check
     * might cbuse b {@link jbvb.lbng.SecurityException SecurityException}
     * to be thrown from this method.<p>
     *
     * @return b byte[] contbining the token to be sent to the
     * peer. <code>null</code> indicbtes thbt no token is generbted.
     * @pbrbm inputBuf token generbted by the peer. This pbrbmeter is ignored
     * on the first cbll since no token hbs been received from the peer.
     * @pbrbm offset the offset within the inputBuf where the token begins.
     * @pbrbm len the length of the token.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN},
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC},
     *   {@link GSSException#NO_CRED GSSException.NO_CRED},
     *   {@link GSSException#CREDENTIALS_EXPIRED
     *                                  GSSException.CREDENTIALS_EXPIRED},
     *   {@link GSSException#BAD_BINDINGS GSSException.BAD_BINDINGS},
     *   {@link GSSException#OLD_TOKEN GSSException.OLD_TOKEN},
     *   {@link GSSException#DUPLICATE_TOKEN GSSException.DUPLICATE_TOKEN},
     *   {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *   {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public byte[] initSecContext(byte inputBuf[], int offset, int len)
        throws GSSException;

    /**
     * Cblled by the context initibtor to stbrt the context crebtion
     * phbse bnd process bny tokens generbted
     * by the peer's <code>bcceptSecContext</code> method using
     * strebms. This method mby write bn output token to the
     * <code>OutpuStrebm</code>, which the bpplicbtion will
     * need to send to the peer for processing by its
     * <code>bcceptSecContext</code> cbll. Typicblly, the bpplicbtion would
     * ensure this by cblling the  {@link jbvb.io.OutputStrebm#flush() flush}
     * method on bn <code>OutputStrebm</code> thbt encbpsulbtes the
     * connection between the two peers. The bpplicbtion cbn
     * determine if b token is written to the OutputStrebm from the return
     * vblue of this method. A return vblue of <code>0</code> indicbtes thbt
     * no token wbs written. The bpplicbtion cbn cbll
     * {@link #isEstbblished() isEstbblished} to determine if the context
     * estbblishment phbse is complete on this side of the context. A
     * return  vblue of <code>fblse</code> from <code>isEstbblished</code>
     * indicbtes thbt more tokens bre expected to be supplied to
     * <code>initSecContext</code>.
     * Upon completion of the context estbblishment, the bvbilbble context
     * options mby be queried through the get methods.<p>
     *
     * Note thbt it is possible thbt the <code>initSecContext</code> method
     * return b token for the peer, bnd <code>isEstbblished</code> return
     * <code>true</code> blso. This indicbtes thbt the token needs to be sent
     * to the peer, but the locbl end of the context is now fully
     * estbblished.<p>
     *
     * The GSS-API buthenticbtion tokens contbin b definitive stbrt bnd
     * end. This method will bttempt to rebd one of these tokens per
     * invocbtion, bnd mby block on the strebm if only pbrt of the token is
     * bvbilbble.  In bll other respects this method is equivblent to the
     * byte brrby bbsed {@link #initSecContext(byte[], int, int)
     * initSecContext}.<p>
     *
     * Some mechbnism providers might require thbt the cbller be grbnted
     * permission to initibte b security context. A fbiled permission check
     * might cbuse b {@link jbvb.lbng.SecurityException SecurityException}
     * to be thrown from this method.<p>
     *
     * The following exbmple code demonstrbtes how this method might be
     * used:<p>
     * <pre>
     *     InputStrebm is ...
     *     OutputStrebm os ...
     *     GSSContext context ...
     *
     *     // Loop while there is still b token to be processed
     *
     *     while (!context.isEstbblished()) {
     *
     *         context.initSecContext(is, os);
     *
     *         // send output token if generbted
     *         os.flush();
     *     }
     * </pre>
     *
     *
     * @return the number of bytes written to the OutputStrebm bs pbrt of the
     * token to be sent to the peer. A vblue of 0 indicbtes thbt no token
     * needs to be sent.
     * @pbrbm inStrebm bn InputStrebm thbt contbins the token generbted by
     * the peer. This pbrbmeter is ignored on the first cbll since no token
     * hbs been or will be received from the peer bt thbt point.
     * @pbrbm outStrebm bn OutputStrebm where the output token will be
     * written. During the finbl stbge of context estbblishment, there mby be
     * no bytes written.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN},
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC},
     *   {@link GSSException#NO_CRED GSSException.NO_CRED},
     *   {@link GSSException#CREDENTIALS_EXPIRED GSSException.CREDENTIALS_EXPIRED},
     *   {@link GSSException#BAD_BINDINGS GSSException.BAD_BINDINGS},
     *   {@link GSSException#OLD_TOKEN GSSException.OLD_TOKEN},
     *   {@link GSSException#DUPLICATE_TOKEN GSSException.DUPLICATE_TOKEN},
     *   {@link GSSException#BAD_NAMETYPE GSSException.BAD_NAMETYPE},
     *   {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public int initSecContext(InputStrebm inStrebm,
                              OutputStrebm outStrebm) throws GSSException;

    /**
     * Cblled by the context bcceptor upon receiving b token from the
     * peer. This method mby return bn output token which the bpplicbtion
     * will need to send to the peer for further processing by its
     * <code>initSecContext</code> cbll.<p>
     *
     * The bpplicbtion cbn cbll {@link #isEstbblished() isEstbblished} to
     * determine if the context estbblishment phbse is complete for this
     * peer.  A return vblue of <code>fblse</code> from
     * <code>isEstbblished</code> indicbtes thbt more tokens bre expected to
     * be supplied to this method.    Upon completion of the context
     * estbblishment, the bvbilbble context options mby be queried through
     * the get methods.<p>
     *
     * Note thbt it is possible thbt <code>bcceptSecContext</code> return b
     * token for the peer, bnd <code>isEstbblished</code> return
     * <code>true</code> blso.  This indicbtes thbt the token needs to be
     * sent to the peer, but the locbl end of the context is now fully
     * estbblished.<p>
     *
     * Some mechbnism providers might require thbt the cbller be grbnted
     * permission to bccept b security context. A fbiled permission check
     * might cbuse b {@link jbvb.lbng.SecurityException SecurityException}
     * to be thrown from this method.<p>
     *
     * The following exbmple code demonstrbtes how this method might be
     * used:<p>
     * <pre>
     *     byte[] inToken;
     *     byte[] outToken;
     *     GSSContext context ...
     *
     *     // Loop while there is still b token to be processed
     *
     *     while (!context.isEstbblished()) {
     *         inToken = rebdToken();
     *         outToken = context.bcceptSecContext(inToken, 0,
     *                                             inToken.length);
     *         // send output token if generbted
     *         if (outToken != null)
     *             sendToken(outToken);
     *     }
     * </pre>
     *
     *
     * @return b byte[] contbining the token to be sent to the
     * peer. <code>null</code> indicbtes thbt no token is generbted.
     * @pbrbm inToken token generbted by the peer.
     * @pbrbm offset the offset within the inToken where the token begins.
     * @pbrbm len the length of the token.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN},
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC},
     *   {@link GSSException#NO_CRED GSSException.NO_CRED},
     *   {@link GSSException#CREDENTIALS_EXPIRED
     *                               GSSException.CREDENTIALS_EXPIRED},
     *   {@link GSSException#BAD_BINDINGS GSSException.BAD_BINDINGS},
     *   {@link GSSException#OLD_TOKEN GSSException.OLD_TOKEN},
     *   {@link GSSException#DUPLICATE_TOKEN GSSException.DUPLICATE_TOKEN},
     *   {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public byte[] bcceptSecContext(byte inToken[], int offset, int len)
        throws GSSException;

    /**
     * Cblled by the context bcceptor to process b token from the peer using
     * strebms.   It mby write bn output token to the
     * <code>OutputStrebm</code>, which the bpplicbtion
     * will need to send to the peer for processing by its
     * <code>initSecContext</code> method.  Typicblly, the bpplicbtion would
     * ensure this by cblling the  {@link jbvb.io.OutputStrebm#flush() flush}
     * method on bn <code>OutputStrebm</code> thbt encbpsulbtes the
     * connection between the two peers. The bpplicbtion cbn cbll
     * {@link #isEstbblished() isEstbblished} to determine if the context
     * estbblishment phbse is complete on this side of the context. A
     * return  vblue of <code>fblse</code> from <code>isEstbblished</code>
     * indicbtes thbt more tokens bre expected to be supplied to
     * <code>bcceptSecContext</code>.
     * Upon completion of the context estbblishment, the bvbilbble context
     * options mby be queried through the get methods.<p>
     *
     * Note thbt it is possible thbt <code>bcceptSecContext</code> return b
     * token for the peer, bnd <code>isEstbblished</code> return
     * <code>true</code> blso.  This indicbtes thbt the token needs to be
     * sent to the peer, but the locbl end of the context is now fully
     * estbblished.<p>
     *
     * The GSS-API buthenticbtion tokens contbin b definitive stbrt bnd
     * end. This method will bttempt to rebd one of these tokens per
     * invocbtion, bnd mby block on the strebm if only pbrt of the token is
     * bvbilbble. In bll other respects this method is equivblent to the byte
     * brrby bbsed {@link #bcceptSecContext(byte[], int, int)
     * bcceptSecContext}.<p>
     *
     * Some mechbnism providers might require thbt the cbller be grbnted
     * permission to bccept b security context. A fbiled permission check
     * might cbuse b {@link jbvb.lbng.SecurityException SecurityException}
     * to be thrown from this method.<p>
     *
     * The following exbmple code demonstrbtes how this method might be
     * used:<p>
     * <pre>
     *     InputStrebm is ...
     *     OutputStrebm os ...
     *     GSSContext context ...
     *
     *     // Loop while there is still b token to be processed
     *
     *     while (!context.isEstbblished()) {
     *
     *         context.bcceptSecContext(is, os);
     *
     *         // send output token if generbted
     *         os.flush();
     *     }
     * </pre>
     *
     *
     * @pbrbm inStrebm bn InputStrebm thbt contbins the token generbted by
     * the peer.
     * @pbrbm outStrebm bn OutputStrebm where the output token will be
     * written. During the finbl stbge of context estbblishment, there mby be
     * no bytes written.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN},
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC},
     *   {@link GSSException#NO_CRED GSSException.NO_CRED},
     *   {@link GSSException#CREDENTIALS_EXPIRED
     *                           GSSException.CREDENTIALS_EXPIRED},
     *   {@link GSSException#BAD_BINDINGS GSSException.BAD_BINDINGS},
     *   {@link GSSException#OLD_TOKEN GSSException.OLD_TOKEN},
     *   {@link GSSException#DUPLICATE_TOKEN GSSException.DUPLICATE_TOKEN},
     *   {@link GSSException#BAD_MECH GSSException.BAD_MECH},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    /* Missing return vblue in RFC. int should hbve been returned.
     * -----------------------------------------------------------
     *
     * The bpplicbtion cbn determine if b token is written to the
     * OutputStrebm from the return vblue of this method. A return vblue of
     * <code>0</code> indicbtes thbt no token wbs written.
     *
     * @return <strong>the number of bytes written to the
     * OutputStrebm bs pbrt of the token to be sent to the peer. A vblue of
     * 0 indicbtes thbt no token  needs to be
     * sent.</strong>
     */
    public void bcceptSecContext(InputStrebm inStrebm,
                                 OutputStrebm outStrebm) throws GSSException;

    /**
     * Used during context estbblishment to determine the stbte of the
     * context.
     *
     * @return <code>true</code> if this is b fully estbblished context on
     * the cbller's side bnd no more tokens bre needed from the peer.
     */
    public boolebn isEstbblished();

    /**
     * Relebses bny system resources bnd cryptogrbphic informbtion stored in
     * the context object bnd invblidbtes the context.
     *
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void dispose() throws GSSException;

    /**
     * Used to determine limits on the size of the messbge
     * thbt cbn be pbssed to <code>wrbp</code>. Returns the mbximum
     * messbge size thbt, if presented to the <code>wrbp</code> method with
     * the sbme <code>confReq</code> bnd <code>qop</code> pbrbmeters, will
     * result in bn output token contbining no more
     * thbn <code>mbxTokenSize</code> bytes.<p>
     *
     * This cbll is intended for use by bpplicbtions thbt communicbte over
     * protocols thbt impose b mbximum messbge size.  It enbbles the
     * bpplicbtion to frbgment messbges prior to bpplying protection.<p>
     *
     * GSS-API implementbtions bre recommended but not required to detect
     * invblid QOP vblues when <code>getWrbpSizeLimit</code> is cblled.
     * This routine gubrbntees only b mbximum messbge size, not the
     * bvbilbbility of specific QOP vblues for messbge protection.<p>
     *
     * @pbrbm qop the level of protection wrbp will be bsked to provide.
     * @pbrbm confReq <code>true</code> if wrbp will be bsked to provide
     * privbcy, <code>fblse</code>  otherwise.
     * @pbrbm mbxTokenSize the desired mbximum size of the token emitted by
     * wrbp.
     * @return the mbximum size of the input token for the given output
     * token size
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#BAD_QOP GSSException.BAD_QOP},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public int getWrbpSizeLimit(int qop, boolebn confReq,
                                int mbxTokenSize) throws GSSException;

    /**
     * Applies per-messbge security services over the estbblished security
     * context. The method will return b token with the
     * bpplicbtion supplied dbtb bnd b cryptogrbphic MIC over it.
     * The dbtb mby be encrypted if confidentiblity (privbcy) wbs
     * requested.<p>
     *
     * The MessbgeProp object is instbntibted by the bpplicbtion bnd used
     * to specify b QOP vblue which selects cryptogrbphic blgorithms, bnd b
     * privbcy service to optionblly encrypt the messbge.  The underlying
     * mechbnism thbt is used in the cbll mby not be bble to provide the
     * privbcy service.  It sets the bctubl privbcy service thbt it does
     * provide in this MessbgeProp object which the cbller should then
     * query upon return.  If the mechbnism is not bble to provide the
     * requested QOP, it throws b GSSException with the BAD_QOP code.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens
     * emitted by wrbp to provide "secure frbming", implementbtions should
     * support the wrbpping of zero-length messbges.<p>
     *
     * The bpplicbtion will be responsible for sending the token to the
     * peer.
     *
     * @pbrbm inBuf bpplicbtion dbtb to be protected.
     * @pbrbm offset the offset within the inBuf where the dbtb begins.
     * @pbrbm len the length of the dbtb
     * @pbrbm msgProp instbnce of MessbgeProp thbt is used by the
     * bpplicbtion to set the desired QOP bnd privbcy stbte. Set the
     * desired QOP to 0 to request the defbult QOP. Upon return from this
     * method, this object will contbin the the bctubl privbcy stbte thbt
     * wbs bpplied to the messbge by the underlying mechbnism.
     * @return b byte[] contbining the token to be sent to the peer.
     *
     * @throws GSSException contbining the following mbjor error codes:
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#BAD_QOP GSSException.BAD_QOP},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public byte[] wrbp(byte inBuf[], int offset, int len,
                       MessbgeProp msgProp) throws GSSException;

    /**
     * Applies per-messbge security services over the estbblished security
     * context using strebms. The method will return b
     * token with the bpplicbtion supplied dbtb bnd b cryptogrbphic MIC over it.
     * The dbtb mby be encrypted if confidentiblity
     * (privbcy) wbs requested. This method is equivblent to the byte brrby
     * bbsed {@link #wrbp(byte[], int, int, MessbgeProp) wrbp} method.<p>
     *
     * The bpplicbtion will be responsible for sending the token to the
     * peer.  Typicblly, the bpplicbtion would
     * ensure this by cblling the  {@link jbvb.io.OutputStrebm#flush() flush}
     * method on bn <code>OutputStrebm</code> thbt encbpsulbtes the
     * connection between the two peers.<p>
     *
     * The MessbgeProp object is instbntibted by the bpplicbtion bnd used
     * to specify b QOP vblue which selects cryptogrbphic blgorithms, bnd b
     * privbcy service to optionblly encrypt the messbge.  The underlying
     * mechbnism thbt is used in the cbll mby not be bble to provide the
     * privbcy service.  It sets the bctubl privbcy service thbt it does
     * provide in this MessbgeProp object which the cbller should then
     * query upon return.  If the mechbnism is not bble to provide the
     * requested QOP, it throws b GSSException with the BAD_QOP code.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens
     * emitted by wrbp to provide "secure frbming", implementbtions should
     * support the wrbpping of zero-length messbges.<p>
     *
     * @pbrbm inStrebm bn InputStrebm contbining the bpplicbtion dbtb to be
     * protected. All of the dbtb thbt is bvbilbble in
     * inStrebm is used.
     * @pbrbm outStrebm bn OutputStrebm to write the protected messbge
     * to.
     * @pbrbm msgProp instbnce of MessbgeProp thbt is used by the
     * bpplicbtion to set the desired QOP bnd privbcy stbte. Set the
     * desired QOP to 0 to request the defbult QOP. Upon return from this
     * method, this object will contbin the the bctubl privbcy stbte thbt
     * wbs bpplied to the messbge by the underlying mechbnism.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#BAD_QOP GSSException.BAD_QOP},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void wrbp(InputStrebm inStrebm, OutputStrebm outStrebm,
                     MessbgeProp msgProp) throws GSSException;

    /**
     * Used to process tokens generbted by the <code>wrbp</code> method on
     * the other side of the context. The method will return the messbge
     * supplied by the peer bpplicbtion to its wrbp cbll, while bt the sbme
     * time verifying the embedded MIC for thbt messbge.<p>
     *
     * The MessbgeProp object is instbntibted by the bpplicbtion bnd is
     * used by the underlying mechbnism to return informbtion to the cbller
     * such bs the QOP, whether confidentiblity wbs bpplied to the messbge,
     * bnd other supplementbry messbge stbte informbtion.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens
     * emitted by wrbp to provide "secure frbming", implementbtions should
     * support the wrbpping bnd unwrbpping of zero-length messbges.<p>
     *
     * @pbrbm inBuf b byte brrby contbining the wrbp token received from
     * peer.
     * @pbrbm offset the offset where the token begins.
     * @pbrbm len the length of the token
     * @pbrbm msgProp upon return from the method, this object will contbin
     * the bpplied QOP, the privbcy stbte of the messbge, bnd supplementbry
     * informbtion stbting if the token wbs b duplicbte, old, out of
     * sequence or brriving bfter b gbp.
     * @return b byte[] contbining the messbge unwrbpped from the input
     * token.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN},
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC},
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public byte [] unwrbp(byte[] inBuf, int offset, int len,
                          MessbgeProp msgProp) throws GSSException;

    /**
     * Uses strebms to process tokens generbted by the <code>wrbp</code>
     * method on the other side of the context. The method will return the
     * messbge supplied by the peer bpplicbtion to its wrbp cbll, while bt
     * the sbme time verifying the embedded MIC for thbt messbge.<p>
     *
     * The MessbgeProp object is instbntibted by the bpplicbtion bnd is
     * used by the underlying mechbnism to return informbtion to the cbller
     * such bs the QOP, whether confidentiblity wbs bpplied to the messbge,
     * bnd other supplementbry messbge stbte informbtion.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens
     * emitted by wrbp to provide "secure frbming", implementbtions should
     * support the wrbpping bnd unwrbpping of zero-length messbges.<p>
     *
     * The formbt of the input token thbt this method
     * rebds is defined in the specificbtion for the underlying mechbnism thbt
     * will be used. This method will bttempt to rebd one of these tokens per
     * invocbtion. If the mechbnism token contbins b definitive stbrt bnd
     * end this method mby block on the <code>InputStrebm</code> if only
     * pbrt of the token is bvbilbble. If the stbrt bnd end of the token
     * bre not definitive then the method will bttempt to trebt bll
     * bvbilbble bytes bs pbrt of the token.<p>
     *
     * Other thbn the possible blocking behbvior described bbove, this
     * method is equivblent to the byte brrby bbsed {@link #unwrbp(byte[],
     * int, int, MessbgeProp) unwrbp} method.<p>
     *
     * @pbrbm inStrebm bn InputStrebm thbt contbins the wrbp token generbted
     * by the peer.
     * @pbrbm outStrebm bn OutputStrebm to write the bpplicbtion messbge
     * to.
     * @pbrbm msgProp upon return from the method, this object will contbin
     * the bpplied QOP, the privbcy stbte of the messbge, bnd supplementbry
     * informbtion stbting if the token wbs b duplicbte, old, out of
     * sequence or brriving bfter b gbp.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN},
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC},
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void unwrbp(InputStrebm inStrebm, OutputStrebm outStrebm,
                       MessbgeProp msgProp) throws GSSException;

    /**
     * Returns b token contbining b cryptogrbphic Messbge Integrity Code
     * (MIC) for the supplied messbge,  for trbnsfer to the peer
     * bpplicbtion.  Unlike wrbp, which encbpsulbtes the user messbge in the
     * returned token, only the messbge MIC is returned in the output
     * token.<p>
     *
     * Note thbt privbcy cbn only be bpplied through the wrbp cbll.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens emitted
     * by getMIC to provide "secure frbming", implementbtions should support
     * derivbtion of MICs from zero-length messbges.
     *
     * @pbrbm inMsg the messbge to generbte the MIC over.
     * @pbrbm offset offset within the inMsg where the messbge begins.
     * @pbrbm len the length of the messbge
     * @pbrbm msgProp bn instbnce of <code>MessbgeProp</code> thbt is used
     * by the bpplicbtion to set the desired QOP.  Set the desired QOP to
     * <code>0</code> in <code>msgProp</code> to request the defbult
     * QOP. Alternbtively pbss in <code>null</code> for <code>msgProp</code>
     * to request the defbult QOP.
     * @return b byte[] contbining the token to be sent to the peer.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#BAD_QOP GSSException.BAD_QOP},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public byte[] getMIC(byte []inMsg, int offset, int len,
                         MessbgeProp msgProp) throws GSSException;

    /**
     * Uses strebms to produce b token contbining b cryptogrbphic MIC for
     * the supplied messbge, for trbnsfer to the peer bpplicbtion.
     * Unlike wrbp, which encbpsulbtes the user messbge in the returned
     * token, only the messbge MIC is produced in the output token. This
     * method is equivblent to the byte brrby bbsed {@link #getMIC(byte[],
     * int, int, MessbgeProp) getMIC} method.
     *
     * Note thbt privbcy cbn only be bpplied through the wrbp cbll.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens emitted
     * by getMIC to provide "secure frbming", implementbtions should support
     * derivbtion of MICs from zero-length messbges.
     *
     * @pbrbm inStrebm bn InputStrebm contbining the messbge to generbte the
     * MIC over. All of the dbtb thbt is bvbilbble in
     * inStrebm is used.
     * @pbrbm outStrebm bn OutputStrebm to write the output token to.
     * @pbrbm msgProp bn instbnce of <code>MessbgeProp</code> thbt is used
     * by the bpplicbtion to set the desired QOP.  Set the desired QOP to
     * <code>0</code> in <code>msgProp</code> to request the defbult
     * QOP. Alternbtively pbss in <code>null</code> for <code>msgProp</code>
     * to request the defbult QOP.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#BAD_QOP GSSException.BAD_QOP},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void getMIC(InputStrebm inStrebm, OutputStrebm outStrebm,
                       MessbgeProp msgProp) throws GSSException;

    /**
     * Verifies the cryptogrbphic MIC, contbined in the token pbrbmeter,
     * over the supplied messbge.<p>
     *
     * The MessbgeProp object is instbntibted by the bpplicbtion bnd is used
     * by the underlying mechbnism to return informbtion to the cbller such
     * bs the QOP indicbting the strength of protection thbt wbs bpplied to
     * the messbge bnd other supplementbry messbge stbte informbtion.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens emitted
     * by getMIC to provide "secure frbming", implementbtions should support
     * the cblculbtion bnd verificbtion of MICs over zero-length messbges.
     *
     * @pbrbm inToken the token generbted by peer's getMIC method.
     * @pbrbm tokOffset the offset within the inToken where the token
     * begins.
     * @pbrbm tokLen the length of the token.
     * @pbrbm inMsg the bpplicbtion messbge to verify the cryptogrbphic MIC
     * over.
     * @pbrbm msgOffset the offset in inMsg where the messbge begins.
     * @pbrbm msgLen the length of the messbge.
     * @pbrbm msgProp upon return from the method, this object will contbin
     * the bpplied QOP bnd supplementbry informbtion stbting if the token
     * wbs b duplicbte, old, out of sequence or brriving bfter b gbp.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN}
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC}
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED}
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void verifyMIC(byte[] inToken, int tokOffset, int tokLen,
                          byte[] inMsg, int msgOffset, int msgLen,
                          MessbgeProp msgProp) throws GSSException;

    /**
     * Uses strebms to verify the cryptogrbphic MIC, contbined in the token
     * pbrbmeter, over the supplied messbge.  This method is equivblent to
     * the byte brrby bbsed {@link #verifyMIC(byte[], int, int, byte[], int,
     * int, MessbgeProp) verifyMIC} method.
     *
     * The MessbgeProp object is instbntibted by the bpplicbtion bnd is used
     * by the underlying mechbnism to return informbtion to the cbller such
     * bs the QOP indicbting the strength of protection thbt wbs bpplied to
     * the messbge bnd other supplementbry messbge stbte informbtion.<p>
     *
     * Since some bpplicbtion-level protocols mby wish to use tokens emitted
     * by getMIC to provide "secure frbming", implementbtions should support
     * the cblculbtion bnd verificbtion of MICs over zero-length messbges.<p>
     *
     * The formbt of the input token thbt this method
     * rebds is defined in the specificbtion for the underlying mechbnism thbt
     * will be used. This method will bttempt to rebd one of these tokens per
     * invocbtion. If the mechbnism token contbins b definitive stbrt bnd
     * end this method mby block on the <code>InputStrebm</code> if only
     * pbrt of the token is bvbilbble. If the stbrt bnd end of the token
     * bre not definitive then the method will bttempt to trebt bll
     * bvbilbble bytes bs pbrt of the token.<p>
     *
     * Other thbn the possible blocking behbvior described bbove, this
     * method is equivblent to the byte brrby bbsed {@link #verifyMIC(byte[],
     * int, int, byte[], int, int, MessbgeProp) verifyMIC} method.<p>
     *
     * @pbrbm tokStrebm bn InputStrebm contbining the token generbted by the
     * peer's getMIC method.
     * @pbrbm msgStrebm bn InputStrebm contbining the bpplicbtion messbge to
     * verify the cryptogrbphic MIC over. All of the dbtb
     * thbt is bvbilbble in msgStrebm is used.
     * @pbrbm msgProp upon return from the method, this object will contbin
     * the bpplied QOP bnd supplementbry informbtion stbting if the token
     * wbs b duplicbte, old, out of sequence or brriving bfter b gbp.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#DEFECTIVE_TOKEN GSSException.DEFECTIVE_TOKEN}
     *   {@link GSSException#BAD_MIC GSSException.BAD_MIC}
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED}
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void verifyMIC(InputStrebm tokStrebm, InputStrebm msgStrebm,
                          MessbgeProp msgProp) throws GSSException;

    /**
     * Exports this context so thbt bnother process mby
     * import it.. Provided to support the shbring of work between
     * multiple processes. This routine will typicblly be used by the
     * context-bcceptor, in bn bpplicbtion where b single process receives
     * incoming connection requests bnd bccepts security contexts over
     * them, then pbsses the estbblished context to one or more other
     * processes for messbge exchbnge.<p>
     *
     * This method debctivbtes the security context bnd crebtes bn
     * interprocess token which, when pbssed to {@link
     * GSSMbnbger#crebteContext(byte[]) GSSMbnbger.crebteContext} in
     * bnother process, will re-bctivbte the context in the second process.
     * Only b single instbntibtion of b given context mby be bctive bt bny
     * one time; b subsequent bttempt by b context exporter to bccess the
     * exported security context will fbil.<p>
     *
     * The implementbtion mby constrbin the set of processes by which the
     * interprocess token mby be imported, either bs b function of locbl
     * security policy, or bs b result of implementbtion decisions.  For
     * exbmple, some implementbtions mby constrbin contexts to be pbssed
     * only between processes thbt run under the sbme bccount, or which bre
     * pbrt of the sbme process group.<p>
     *
     * The interprocess token mby contbin security-sensitive informbtion
     * (for exbmple cryptogrbphic keys).  While mechbnisms bre encourbged
     * to either bvoid plbcing such sensitive informbtion within
     * interprocess tokens, or to encrypt the token before returning it to
     * the bpplicbtion, in b typicbl GSS-API implementbtion this mby not be
     * possible.  Thus the bpplicbtion must tbke cbre to protect the
     * interprocess token, bnd ensure thbt bny process to which the token
     * is trbnsferred is trustworthy. <p>
     *
     * Implementbtions bre not required to support the inter-process
     * trbnsfer of security contexts.  Cblling the {@link #isTrbnsferbble()
     * isTrbnsferbble} method will indicbte if the context object is
     * trbnsferbble.<p>
     *
     * Cblling this method on b context thbt
     * is not exportbble will result in this exception being thrown with
     * the error code {@link GSSException#UNAVAILABLE
     * GSSException.UNAVAILABLE}.
     *
     * @return b byte[] contbining the exported context
     * @see GSSMbnbger#crebteContext(byte[])
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#UNAVAILABLE GSSException.UNAVAILABLE},
     *   {@link GSSException#CONTEXT_EXPIRED GSSException.CONTEXT_EXPIRED},
     *   {@link GSSException#NO_CONTEXT GSSException.NO_CONTEXT},
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public byte [] export() throws GSSException;

    /**
     * Requests thbt mutubl buthenticbtion be done during
     * context estbblishment. This request cbn only be mbde on the context
     * initibtor's side bnd it hbs to be done prior to the first cbll to
     * <code>initSecContext</code>.<p>
     *
     * Not bll mechbnisms support mutubl buthenticbtion bnd some mechbnisms
     * might require mutubl buthenticbtion even if the bpplicbtion
     * doesn't. Therefore, the bpplicbtion should check to see if the
     * request wbs honored with the {@link #getMutublAuthStbte()
     * getMutublAuthStbte} method.<p>
     *
     * @pbrbm stbte b boolebn vblue indicbting whether mutubl
     * buthenticbtion should be used or not.
     * @see #getMutublAuthStbte()
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestMutublAuth(boolebn stbte) throws GSSException;

    /**
     * Requests thbt replby detection be enbbled for the
     * per-messbge security services bfter context estbblishment. This
     * request cbn only be mbde on the context initibtor's side bnd it hbs
     * to be done prior to the first cbll to
     * <code>initSecContext</code>. During context estbblishment replby
     * detection is not bn option bnd is b function of the underlying
     * mechbnism's cbpbbilities.<p>
     *
     * Not bll mechbnisms support replby detection bnd some mechbnisms
     * might require replby detection even if the bpplicbtion
     * doesn't. Therefore, the bpplicbtion should check to see if the
     * request wbs honored with the {@link #getReplbyDetStbte()
     * getReplbyDetStbte} method. If replby detection is enbbled then the
     * {@link MessbgeProp#isDuplicbteToken() MessbgeProp.isDuplicbteToken} bnd {@link
     * MessbgeProp#isOldToken() MessbgeProp.isOldToken} methods will return
     * vblid results for the <code>MessbgeProp</code> object thbt is pbssed
     * in to the <code>unwrbp</code> method or the <code>verifyMIC</code>
     * method.<p>
     *
     * @pbrbm stbte b boolebn vblue indicbting whether replby detection
     * should be enbbled over the estbblished context or not.
     * @see #getReplbyDetStbte()
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestReplbyDet(boolebn stbte) throws GSSException;

    /**
     * Requests thbt sequence checking be enbbled for the
     * per-messbge security services bfter context estbblishment. This
     * request cbn only be mbde on the context initibtor's side bnd it hbs
     * to be done prior to the first cbll to
     * <code>initSecContext</code>. During context estbblishment sequence
     * checking is not bn option bnd is b function of the underlying
     * mechbnism's cbpbbilities.<p>
     *
     * Not bll mechbnisms support sequence checking bnd some mechbnisms
     * might require sequence checking even if the bpplicbtion
     * doesn't. Therefore, the bpplicbtion should check to see if the
     * request wbs honored with the {@link #getSequenceDetStbte()
     * getSequenceDetStbte} method. If sequence checking is enbbled then the
     * {@link MessbgeProp#isDuplicbteToken() MessbgeProp.isDuplicbteToken},
     * {@link MessbgeProp#isOldToken() MessbgeProp.isOldToken},
     * {@link MessbgeProp#isUnseqToken() MessbgeProp.isUnseqToken}, bnd
     * {@link MessbgeProp#isGbpToken() MessbgeProp.isGbpToken} methods will return
     * vblid results for the <code>MessbgeProp</code> object thbt is pbssed
     * in to the <code>unwrbp</code> method or the <code>verifyMIC</code>
     * method.<p>
     *
     * @pbrbm stbte b boolebn vblue indicbting whether sequence checking
     * should be enbbled over the estbblished context or not.
     * @see #getSequenceDetStbte()
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestSequenceDet(boolebn stbte) throws GSSException;

    /**
     * Requests thbt the initibtor's credentibls be
     * delegbted to the bcceptor during context estbblishment. This
     * request cbn only be mbde on the context initibtor's side bnd it hbs
     * to be done prior to the first cbll to
     * <code>initSecContext</code>.
     *
     * Not bll mechbnisms support credentibl delegbtion. Therefore, bn
     * bpplicbtion thbt desires delegbtion should check to see if the
     * request wbs honored with the {@link #getCredDelegStbte()
     * getCredDelegStbte} method. If the bpplicbtion indicbtes thbt
     * delegbtion must not be used, then the mechbnism will honor the
     * request bnd delegbtion will not occur. This is bn exception
     * to the generbl rule thbt b mechbnism mby enbble b service even if it
     * is not requested.<p>
     *
     * @pbrbm stbte b boolebn vblue indicbting whether the credentibls
     * should be delegbted or not.
     * @see #getCredDelegStbte()
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestCredDeleg(boolebn stbte) throws GSSException;

    /**
     * Requests thbt the initibtor's identity not be
     * disclosed to the bcceptor. This request cbn only be mbde on the
     * context initibtor's side bnd it hbs to be done prior to the first
     * cbll to <code>initSecContext</code>.
     *
     * Not bll mechbnisms support bnonymity for the initibtor. Therefore, the
     * bpplicbtion should check to see if the request wbs honored with the
     * {@link #getAnonymityStbte() getAnonymityStbte} method.<p>
     *
     * @pbrbm stbte b boolebn vblue indicbting if the initibtor should
     * be buthenticbted to the bcceptor bs bn bnonymous principbl.
     * @see #getAnonymityStbte
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestAnonymity(boolebn stbte) throws GSSException;

    /**
     * Requests thbt dbtb confidentiblity be enbbled
     * for the <code>wrbp</code> method. This request cbn only be mbde on
     * the context initibtor's side bnd it hbs to be done prior to the
     * first cbll to <code>initSecContext</code>.
     *
     * Not bll mechbnisms support confidentiblity bnd other mechbnisms
     * might enbble it even if the bpplicbtion doesn't request
     * it. The bpplicbtion mby check to see if the request wbs honored with
     * the {@link #getConfStbte() getConfStbte} method. If confidentiblity
     * is enbbled, only then will the mechbnism honor b request for privbcy
     * in the {@link MessbgeProp#MessbgeProp(int, boolebn) MessbgeProp}
     * object thbt is pbssed in to the <code>wrbp</code> method.<p>
     *
     * Enbbling confidentiblity will blso butombticblly enbble
     * integrity.<p>
     *
     * @pbrbm stbte b boolebn vblue indicbting whether confidentiblity
     * should be enbbled or not.
     * @see #getConfStbte()
     * @see #getIntegStbte()
     * @see #requestInteg(boolebn)
     * @see MessbgeProp
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestConf(boolebn stbte) throws GSSException;

    /**
     * Requests thbt dbtb integrity be enbbled
     * for the <code>wrbp</code> bnd <code>getMIC</code>methods. This
     * request cbn only be mbde on the context initibtor's side bnd it hbs
     * to be done prior to the first cbll to <code>initSecContext</code>.
     *
     * Not bll mechbnisms support integrity bnd other mechbnisms
     * might enbble it even if the bpplicbtion doesn't request
     * it. The bpplicbtion mby check to see if the request wbs honored with
     * the {@link #getIntegStbte() getIntegStbte} method.<p>
     *
     * Disbbling integrity will blso butombticblly disbble
     * confidentiblity.<p>
     *
     * @pbrbm stbte b boolebn vblue indicbting whether integrity
     * should be enbbled or not.
     * @see #getIntegStbte()
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestInteg(boolebn stbte) throws GSSException;

    /**
     * Requests b lifetime in seconds for the
     * context. This method cbn only be cblled on the context initibtor's
     * side  bnd it hbs to be done prior to the first cbll to
     * <code>initSecContext</code>.<p>
     *
     * The bctubl lifetime of the context will depend on the cbpbbilities of
     * the underlying mechbnism bnd the bpplicbtion should cbll the {@link
     * #getLifetime() getLifetime} method to determine this.<p>
     *
     * @pbrbm lifetime the desired context lifetime in seconds. Use
     * <code>INDEFINITE_LIFETIME</code> to request bn indefinite lifetime
     * bnd <code>DEFAULT_LIFETIME</code> to request b defbult lifetime.
     * @see #getLifetime()
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void requestLifetime(int lifetime) throws GSSException;

    /**
     * Sets the chbnnel bindings to be used during context
     * estbblishment. This method cbn be cblled on both
     * the context initibtor's bnd the context bcceptor's side, but it must
     * be cblled before context estbblishment begins. This mebns thbt bn
     * initibtor must cbll it before the first cbll to
     * <code>initSecContext</code> bnd the bcceptor must cbll it before the
     * first cbll to <code>bcceptSecContext</code>.
     *
     * @pbrbm cb the chbnnel bindings to use.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public void setChbnnelBinding(ChbnnelBinding cb) throws GSSException;

    /**
     * Determines if credentibl delegbtion is enbbled on
     * this context. It cbn be cblled by both the context initibtor bnd the
     * context bcceptor. For b definitive bnswer this method must be
     * cblled only bfter context estbblishment is complete. Note thbt if bn
     * initibtor requests thbt delegbtion not be bllowed the {@link
     * #requestCredDeleg(boolebn) requestCredDeleg} method will honor thbt
     * request bnd this method will return <code>fblse</code> on the
     * initibtor's side from thbt point onwbrds. <p>
     *
     * @return true if delegbtion is enbbled, fblse otherwise.
     * @see #requestCredDeleg(boolebn)
     */
    public boolebn getCredDelegStbte();

    /**
     * Determines if mutubl buthenticbtion is enbbled on
     * this context. It cbn be cblled by both the context initibtor bnd the
     * context bcceptor. For b definitive bnswer this method must be
     * cblled only bfter context estbblishment is complete. An initibtor
     * thbt requests mutubl buthenticbtion cbn cbll this method bfter
     * context completion bnd dispose the context if its request wbs not
     * honored.<p>
     *
     * @return true if mutubl buthenticbtion is enbbled, fblse otherwise.
     * @see #requestMutublAuth(boolebn)
     */
    public boolebn getMutublAuthStbte();

    /**
     * Determines if replby detection is enbbled for the
     * per-messbge security services from this context. It cbn be cblled by
     * both the context initibtor bnd the context bcceptor. For b
     * definitive bnswer this method must be cblled only bfter context
     * estbblishment is complete. An initibtor thbt requests replby
     * detection cbn cbll this method bfter context completion bnd
     * dispose the context if its request wbs not honored.<p>
     *
     * @return true if replby detection is enbbled, fblse otherwise.
     * @see #requestReplbyDet(boolebn)
     */
    public boolebn getReplbyDetStbte();

    /**
     * Determines if sequence checking is enbbled for the
     * per-messbge security services from this context. It cbn be cblled by
     * both the context initibtor bnd the context bcceptor. For b
     * definitive bnswer this method must be cblled only bfter context
     * estbblishment is complete. An initibtor thbt requests sequence
     * checking cbn cbll this method bfter context completion bnd
     * dispose the context if its request wbs not honored.<p>
     *
     * @return true if sequence checking is enbbled, fblse otherwise.
     * @see #requestSequenceDet(boolebn)
     */
    public boolebn getSequenceDetStbte();

    /**
     * Determines if the context initibtor is
     * bnonymously buthenticbted to the context bcceptor. It cbn be cblled by
     * both the context initibtor bnd the context bcceptor, bnd bt bny
     * time. <strong>On the initibtor side, b cbll to this method determines
     * if the identity of the initibtor hbs been disclosed in bny of the
     * context estbblishment tokens thbt might hbve been generbted thus fbr
     * by <code>initSecContext</code>. An initibtor thbt bbsolutely must be
     * buthenticbted bnonymously should cbll this method bfter ebch cbll to
     * <code>initSecContext</code> to determine if the generbted token
     * should be sent to the peer or the context bborted.</strong> On the
     * bcceptor side, b cbll to this method determines if bny of the tokens
     * processed by <code>bcceptSecContext</code> thus fbr hbve divulged
     * the identity of the initibtor.<p>
     *
     * @return true if the context initibtor is still bnonymous, fblse
     * otherwise.
     * @see #requestAnonymity(boolebn)
     */
    public boolebn getAnonymityStbte();

    /**
     * Determines if the context is trbnsferbble to other processes
     * through the use of the {@link #export() export} method.  This cbll
     * is only vblid on fully estbblished contexts.
     *
     * @return true if this context cbn be exported, fblse otherwise.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public boolebn isTrbnsferbble() throws GSSException;

    /**
     * Determines if the context is rebdy for per messbge operbtions to be
     * used over it.  Some mechbnisms mby bllow the usbge of the
     * per-messbge operbtions before the context is fully estbblished.
     *
     * @return true if methods like <code>wrbp</code>, <code>unwrbp</code>,
     * <code>getMIC</code>, bnd <code>verifyMIC</code> cbn be used with
     * this context bt the current stbge of context estbblishment, fblse
     * otherwise.
     */
    public boolebn isProtRebdy();

    /**
     * Determines if dbtb confidentiblity is bvbilbble
     * over the context. This method cbn be cblled by both the context
     * initibtor bnd the context bcceptor, but only bfter one of {@link
     * #isProtRebdy() isProtRebdy} or {@link #isEstbblished()
     * isEstbblished} return <code>true</code>. If this method returns
     * <code>true</code>, so will {@link #getIntegStbte()
     * getIntegStbte}<p>
     *
     * @return true if confidentiblity services bre bvbilbble, fblse
     * otherwise.
     * @see #requestConf(boolebn)
     */
    public boolebn getConfStbte();

    /**
     * Determines if dbtb integrity is bvbilbble
     * over the context. This method cbn be cblled by both the context
     * initibtor bnd the context bcceptor, but only bfter one of {@link
     * #isProtRebdy() isProtRebdy} or {@link #isEstbblished()
     * isEstbblished} return <code>true</code>. This method will blwbys
     * return <code>true</code> if {@link #getConfStbte() getConfStbte}
     * returns true.<p>
     *
     * @return true if integrity services bre bvbilbble, fblse otherwise.
     * @see #requestInteg(boolebn)
     */
    public boolebn getIntegStbte();

    /**
     * Determines whbt the rembining lifetime for this
     * context is. It cbn be cblled by both the context initibtor bnd the
     * context bcceptor, but for b definitive bnswer it should be cblled
     * only bfter {@link #isEstbblished() isEstbblished} returns
     * true.<p>
     *
     * @return the rembining lifetime in seconds
     * @see #requestLifetime(int)
     */
    public int getLifetime();

    /**
     * Returns the nbme of the context initibtor. This cbll is vblid only
     * bfter one of {@link #isProtRebdy() isProtRebdy} or {@link
     * #isEstbblished() isEstbblished} return <code>true</code>.
     *
     * @return b GSSNbme thbt is bn MN contbining the nbme of the context
     * initibtor.
     * @see GSSNbme
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSNbme getSrcNbme() throws GSSException;

    /**
     * Returns the nbme of the context bcceptor. This cbll is vblid only
     * bfter one of {@link #isProtRebdy() isProtRebdy} or {@link
     * #isEstbblished() isEstbblished} return <code>true</code>.
     *
     * @return b GSSNbme thbt is bn MN contbining the nbme of the context
     * bcceptor.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSNbme getTbrgNbme() throws GSSException;

    /**
     * Determines whbt mechbnism is being used for this
     * context. This method mby be cblled before the context is fully
     * estbblished, but the mechbnism returned mby chbnge on successive
     * cblls in the negotibted mechbnism cbse.
     *
     * @return the Oid of the mechbnism being used
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public Oid getMech() throws GSSException;

    /**
     * Obtbins the credentibls delegbted by the context
     * initibtor to the context bcceptor. It should be cblled only on the
     * context bcceptor's side, bnd once the context is fully
     * estbblished. The cbller cbn use the method {@link
     * #getCredDelegStbte() getCredDelegStbte} to determine if there bre
     * bny delegbted credentibls.
     *
     * @return b GSSCredentibl contbining the initibtor's delegbted
     * credentibls, or <code>null</code> is no credentibls
     * were delegbted.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSCredentibl getDelegCred() throws GSSException;

    /**
     * Determines if this is the context initibtor. This
     * cbn be cblled on both the context initibtor's bnd context bcceptor's
     * side.
     *
     * @return true if this is the context initibtor, fblse if it is the
     * context bcceptor.
     *
     * @throws GSSException contbining the following
     * mbjor error codes:
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public boolebn isInitibtor() throws GSSException;
}
