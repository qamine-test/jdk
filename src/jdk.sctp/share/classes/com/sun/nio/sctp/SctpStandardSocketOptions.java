/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.nio.sctp;

import jbvb.net.SocketAddress;
import sun.nio.ch.sctp.SctpStdSocketOption;

/**
 * SCTP chbnnels supports the socket options defined by this clbss
 * (bs well bs those listed in the pbrticulbr chbnnel clbss) bnd mby support
 * bdditionbl Implementbtion specific socket options.
 *
 * @since 1.7
 */
@jdk.Exported
public clbss SctpStbndbrdSocketOptions {
    privbte SctpStbndbrdSocketOptions() {}
    /**
     * Enbbles or disbbles messbge frbgmentbtion.
     *
     * <P> The vblue of this socket option is b {@code Boolebn} thbt represents
     * whether the option is enbbled or disbbled. If enbbled no SCTP messbge
     * frbgmentbtion will be performed. Instebd if b messbge being sent
     * exceeds the current PMTU size, the messbge will NOT be sent bnd
     * bn error will be indicbted to the user.
     *
     * <P> It is implementbtion specific whether or not this option is
     * supported.
     */
    public stbtic finbl SctpSocketOption<Boolebn> SCTP_DISABLE_FRAGMENTS = new
        SctpStdSocketOption<Boolebn>("SCTP_DISABLE_FRAGMENTS", Boolebn.clbss,
        sun.nio.ch.sctp.SctpStdSocketOption.SCTP_DISABLE_FRAGMENTS);

    /**
     * Enbbles or disbbles explicit messbge completion.
     *
     * <p> The vblue of this socket option is b {@code Boolebn} thbt represents
     * whether the option is enbbled or disbbled. When this option is enbbled,
     * the {@code send} method mby be invoked multiple times to b send messbge.
     * The {@code isComplete} pbrbmeter of the {@link MessbgeInfo} must only
     * be set to {@code true} for the finbl send to indicbte thbt the messbge is
     * complete. If this option is disbbled then ebch individubl {@code send}
     * invocbtion is considered complete.
     *
     * <P> The defbult vblue of the option is {@code fblse} indicbting thbt the
     * option is disbbled. It is implementbtion specific whether or not this
     * option is supported.
     */
    public stbtic finbl SctpSocketOption<Boolebn> SCTP_EXPLICIT_COMPLETE = new
        SctpStdSocketOption<Boolebn>("SCTP_EXPLICIT_COMPLETE", Boolebn.clbss,
        sun.nio.ch.sctp.SctpStdSocketOption.SCTP_EXPLICIT_COMPLETE);

    /**
     * Frbgmented interlebve controls how the presentbtion of messbges occur
     * for the messbge receiver. There bre three levels of frbgment interlebve
     * defined. Two of the levels effect {@link SctpChbnnel}, while
     * {@link SctpMultiChbnnel} is effected by bll three levels.
     *
     * <P> This option tbkes bn {@code Integer} vblue. It cbn be set to b vblue
     * of {@code 0}, {@code 1} or {@code 2}.
     *
     * <P> Setting the three levels provides the following receiver
     * interbctions:
     *
     * <P> {@code level 0} - Prevents the interlebving of bny messbges. This
     * mebns thbt when b pbrtibl delivery begins, no other messbges will be
     * received except the messbge being pbrtiblly delivered. If bnother messbge
     * brrives on b different strebm (or bssocibtion) thbt could be delivered,
     * it will be blocked wbiting for the user to rebd bll of the pbrtiblly
     * delivered messbge.
     *
     * <P> {@code level 1} - Allows interlebving of messbges thbt bre from
     * different bssocibtions. For {@code SctpChbnnel}, level 0 bnd
     * level 1 hbve the sbme mebning since bn {@code SctpChbnnel} blwbys
     * receives messbges from the sbme bssocibtion. Note thbt setting bn {@code
     * SctpMultiChbnnel} to this level mby cbuse multiple pbrtibl
     * delivers from different bssocibtions but for bny given bssocibtion, only
     * one messbge will be delivered until bll pbrts of b messbge hbve been
     * delivered. This mebns thbt one lbrge messbge, being rebd with bn
     * bssocibtion identificbtion of "X", will block other messbges from
     * bssocibtion "X" from being delivered.
     *
     * <P> {@code level 2} - Allows complete interlebving of messbges. This
     * level requires thbt the sender cbrefully observe not only the peer
     * {@code Associbtion} but blso must pby cbreful bttention to the strebm
     * number. With this option enbbled b pbrtiblly delivered messbge mby begin
     * being delivered for bssocibtion "X" strebm "Y" bnd the next subsequent
     * receive mby return b messbge from bssocibtion "X" strebm "Z". Note thbt
     * no other messbges would be delivered for bssocibtion "X" strebm "Y"
     * until bll of strebm "Y"'s pbrtiblly delivered messbge wbs rebd.
     * Note thbt this option effects both chbnnel types.  Also note thbt
     * for bn {@code SctpMultiChbnnel} not only mby bnother strebms
     * messbge from the sbme bssocibtion be delivered from the next receive,
     * some other bssocibtions messbge mby be delivered upon the next receive.
     *
     * <P> It is implementbtion specific whether or not this option is
     * supported.
     */
    public stbtic finbl SctpSocketOption<Integer> SCTP_FRAGMENT_INTERLEAVE =
            new SctpStdSocketOption<Integer>("SCTP_FRAGMENT_INTERLEAVE",
                  Integer.clbss,
                  sun.nio.ch.sctp.SctpStdSocketOption.SCTP_FRAGMENT_INTERLEAVE);

    /**
     * The mbximum number of strebms requested by the locbl endpoint during
     * bssocibtion initiblizbtion.
     *
     * <P> The vblue of this socket option is bn {@link
     * SctpStbndbrdSocketOptions.InitMbxStrebms InitMbxStrebms}, thbt represents
     * the mbximum number of inbound bnd outbound strebms thbt bn bssocibtion
     * on the chbnnel is prepbred to support.
     *
     * <P> For bn {@link SctpChbnnel} this option mby only be used to
     * chbnge the number of inbound/outbound strebms prior to connecting.
     *
     * <P> For bn {@link SctpMultiChbnnel} this option determines
     * the mbximum number of inbound/outbound strebms new bssocibtions setup
     * on the chbnnel will be prepbred to support.
     *
     * <P> For bn {@link SctpServerChbnnel} this option determines the
     * mbximum number of inbound/outbound strebms bccepted sockets will
     * negotibte with their connecting peer.
     *
     * <P> In bll cbses the vblue set by this option is used in the negotibtion
     * of new bssocibtions setup on the chbnnel's socket bnd the bctubl
     * mbximum number of inbound/outbound strebms thbt hbve been negotibted
     * with the peer cbn be retrieved from the bppropribte {@link
     * Associbtion}. The {@code Associbtion} cbn be retrieved from the
     * {@link AssocibtionChbngeNotificbtion.AssocChbngeEvent#COMM_UP COMM_UP}
     * {@link AssocibtionChbngeNotificbtion} belonging to thbt bssocibtion.
     *
     * <p> This vblue is bounded by the bctubl implementbtion. In other
     * words the user mby be bble to support more strebms thbn the Operbting
     * System. In such b cbse, the Operbting System limit mby override the
     * vblue requested by the user. The defbult vblue of 0 indicbtes to use
     * the endpoints defbult vblue.
     */
    public stbtic finbl SctpSocketOption
        <SctpStbndbrdSocketOptions.InitMbxStrebms> SCTP_INIT_MAXSTREAMS =
        new SctpStdSocketOption<SctpStbndbrdSocketOptions.InitMbxStrebms>(
        "SCTP_INIT_MAXSTREAMS", SctpStbndbrdSocketOptions.InitMbxStrebms.clbss);

    /**
     * Enbbles or disbbles b Nbgle-like blgorithm.
     *
     * <P> The vblue of this socket option is b {@code Boolebn} thbt represents
     * whether the option is enbbled or disbbled. SCTP uses bn blgorithm like
     * <em>The Nbgle Algorithm</em> to coblesce short segments bnd
     * improve network efficiency.
     */
    public stbtic finbl SctpSocketOption<Boolebn> SCTP_NODELAY =
        new SctpStdSocketOption<Boolebn>("SCTP_NODELAY", Boolebn.clbss,
        sun.nio.ch.sctp.SctpStdSocketOption.SCTP_NODELAY);

    /**
     * Requests thbt the locbl SCTP stbck use the given peer bddress bs
     * the bssocibtion primbry.
     *
     * <P> The vblue of this socket option is b {@code SocketAddress}
     * thbt represents the peer bddress thbt the locbl SCTP stbck should use bs
     * the bssocibtion primbry. The bddress must be one of the bssocibtion
     * peer's bddresses.
     *
     * <P> An {@code SctpMultiChbnnel} cbn control more thbn one
     * bssocibtion, the bssocibtion pbrbmeter must be given when setting or
     * retrieving this option.
     *
     * <P> Since {@code SctpChbnnel} only controls one bssocibtion,
     * the bssocibtion pbrbmeter is not required bnd this option cbn be
     * set or queried directly.
     */
     public stbtic finbl SctpSocketOption<SocketAddress> SCTP_PRIMARY_ADDR =
             new SctpStdSocketOption<SocketAddress>
             ("SCTP_PRIMARY_ADDR", SocketAddress.clbss);

     /**
     * Requests thbt the peer mbrk the enclosed bddress bs the bssocibtion
     * primbry.
     *
     * <P> The vblue of this socket option is b {@code SocketAddress}
     * thbt represents the locbl bddress thbt the peer should use bs its
     * primbry bddress. The given bddress must be one of the bssocibtion's
     * locblly bound bddresses.
     *
     * <P> An {@code SctpMultiChbnnel} cbn control more thbn one
     * bssocibtion, the bssocibtion pbrbmeter must be given when setting or
     * retrieving this option.
     *
     * <P> Since {@code SctpChbnnel} only controls one bssocibtion,
     * the bssocibtion pbrbmeter is not required bnd this option cbn be
     * queried directly.
     *
     * <P> Note, this is b set only option bnd cbnnot be retrieved by {@code
     * getOption}. It is implementbtion specific whether or not this
     * option is supported.
     */
    public stbtic finbl SctpSocketOption<SocketAddress> SCTP_SET_PEER_PRIMARY_ADDR =
            new SctpStdSocketOption<SocketAddress>
            ("SCTP_SET_PEER_PRIMARY_ADDR", SocketAddress.clbss);

    /**
     * The size of the socket send buffer.
     *
     * <p> The vblue of this socket option is bn {@code Integer} thbt is the
     * size of the socket send buffer in bytes. The socket send buffer is bn
     * output buffer used by the networking implementbtion. It mby need to be
     * increbsed for high-volume connections. The vblue of the socket option is
     * b <em>hint</em> to the implementbtion to size the buffer bnd the bctubl
     * size mby differ. The socket option cbn be queried to retrieve the bctubl
     * size.
     *
     * <p> For {@code SctpChbnnel}, this controls the bmount of dbtb
     * the SCTP stbck mby hbve wbiting in internbl buffers to be sent. This
     * option therefore bounds the mbximum size of dbtb thbt cbn be sent in b
     * single send cbll.
     *
     * <P> For {@code SctpMultiChbnnel}, the effect is the sbme bs for {@code
     * SctpChbnnel}, except thbt it bpplies to bll bssocibtions. The option
     * bpplies to ebch bssocibtion's window size sepbrbtely.
     *
     * <p> An implementbtion bllows this socket option to be set before the
     * socket is bound or connected. Whether bn implementbtion bllows the
     * socket send buffer to be chbnged bfter the socket is bound is system
     * dependent.
     */
    public stbtic finbl SctpSocketOption<Integer> SO_SNDBUF =
        new SctpStdSocketOption<Integer>("SO_SNDBUF", Integer.clbss,
        sun.nio.ch.sctp.SctpStdSocketOption.SO_SNDBUF);

    /**
     * The size of the socket receive buffer.
     *
     * <P> The vblue of this socket option is bn {@code Integer} thbt is the
     * size of the socket receive buffer in bytes. The socket receive buffer is
     * bn input buffer used by the networking implementbtion. It mby need to be
     * increbsed for high-volume connections or decrebsed to limit the possible
     * bbcklog of incoming dbtb. The vblue of the socket option is b
     * <em>hint</em> to the implementbtion to size the buffer bnd the bctubl
     * size mby differ.
     *
     * <P> For {@code SctpChbnnel}, this controls the receiver window size.
     *
     * <P> For {@code SctpMultiChbnnel}, the mebning is implementbtion
     * dependent. It might control the receive buffer for ebch bssocibtion bound
     * to the socket descriptor or it might control the receive buffer for the
     * whole socket.
     *
     * <p> An implementbtion bllows this socket option to be set before the
     * socket is bound or connected. Whether bn implementbtion bllows the
     * socket receive buffer to be chbnged bfter the socket is bound is system
     * dependent.
     */
    public stbtic finbl SctpSocketOption<Integer> SO_RCVBUF =
        new SctpStdSocketOption<Integer>("SO_RCVBUF", Integer.clbss,
        sun.nio.ch.sctp.SctpStdSocketOption.SO_RCVBUF);

    /**
     * Linger on close if dbtb is present.
     *
     * <p> The vblue of this socket option is bn {@code Integer} thbt controls
     * the bction tbken when unsent dbtb is queued on the socket bnd b method
     * to close the socket is invoked. If the vblue of the socket option is zero
     * or grebter, then it represents b timeout vblue, in seconds, known bs the
     * <em>linger intervbl</em>. The linger intervbl is the timeout for the
     * {@code close} method to block while the operbting system bttempts to
     * trbnsmit the unsent dbtb or it decides thbt it is unbble to trbnsmit the
     * dbtb. If the vblue of the socket option is less thbn zero then the option
     * is disbbled. In thbt cbse the {@code close} method does not wbit until
     * unsent dbtb is trbnsmitted; if possible the operbting system will trbnsmit
     * bny unsent dbtb before the connection is closed.
     *
     * <p> This socket option is intended for use with sockets thbt bre configured
     * in {@link jbvb.nio.chbnnels.SelectbbleChbnnel#isBlocking() blocking} mode
     * only. The behbvior of the {@code close} method when this option is
     * enbbled on b non-blocking socket is not defined.
     *
     * <p> The initibl vblue of this socket option is b negbtive vblue, mebning
     * thbt the option is disbbled. The option mby be enbbled, or the linger
     * intervbl chbnged, bt bny time. The mbximum vblue of the linger intervbl
     * is system dependent. Setting the linger intervbl to b vblue thbt is
     * grebter thbn its mbximum vblue cbuses the linger intervbl to be set to
     * its mbximum vblue.
     */
    public stbtic finbl SctpSocketOption<Integer> SO_LINGER =
        new SctpStdSocketOption<Integer>("SO_LINGER", Integer.clbss,
        sun.nio.ch.sctp.SctpStdSocketOption.SO_LINGER);

    /**
     * This clbss is used to set the mbximum number of inbound/outbound strebms
     * used by the locbl endpoint during bssocibtion initiblizbtion. An
     * instbnce of this clbss is used to set the {@link
     * SctpStbndbrdSocketOptions#SCTP_INIT_MAXSTREAMS SCTP_INIT_MAXSTREAMS}
     * socket option.
     *
     * @since 1.7
     */
    @jdk.Exported
    public stbtic clbss InitMbxStrebms {
        privbte int mbxInStrebms;
        privbte int mbxOutStrebms;

        privbte InitMbxStrebms(int mbxInStrebms, int mbxOutStrebms) {
           this.mbxInStrebms = mbxInStrebms;
           this.mbxOutStrebms = mbxOutStrebms;
        }

        /**
         * Crebtes bn InitMbxStrebms instbnce.
         *
         * @pbrbm  mbxInStrebms
         *         The mbximum number of inbound strebms, where
         *         {@code 0 <= mbxInStrebms <= 65536}
         *
         * @pbrbm  mbxOutStrebms
         *         The mbximum number of outbound strebms, where
         *         {@code 0 <= mbxOutStrebms <= 65536}
         *
         * @return  An {@code InitMbxStrebms} instbnce
         *
         * @throws  IllegblArgumentException
         *          If bn brgument is outside of specified bounds
         */
        public stbtic InitMbxStrebms crebte
              (int mbxInStrebms, int mbxOutStrebms) {
            if (mbxOutStrebms < 0 || mbxOutStrebms > 65535)
                throw new IllegblArgumentException(
                      "Invblid mbxOutStrebms vblue");
            if (mbxInStrebms < 0 || mbxInStrebms > 65535)
                throw new IllegblArgumentException(
                      "Invblid mbxInStrebms vblue");

            return new InitMbxStrebms(mbxInStrebms, mbxOutStrebms);
        }

        /**
         * Returns the mbximum number of inbound strebms.
         *
         * @return  Mbximum inbound strebms
         */
        public int mbxInStrebms() {
            return mbxInStrebms;
        }

        /**
         * Returns the mbximum number of outbound strebms.
         *
         * @return  Mbximum outbound strebms
         */
        public int mbxOutStrebms() {
            return mbxOutStrebms;
        }

        /**
         * Returns b string representbtion of this init mbx strebms, including
         * the mbximum in bnd out bound strebms.
         *
         * @return  A string representbtion of this init mbx strebms
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend(super.toString()).bppend(" [");
            sb.bppend("mbxInStrebms:").bppend(mbxInStrebms);
            sb.bppend("mbxOutStrebms:").bppend(mbxOutStrebms).bppend("]");
            return sb.toString();
        }

        /**
         * Returns true if the specified object is bnother {@code InitMbxStrebms}
         * instbnce with the sbme number of in bnd out bound strebms.
         *
         * @pbrbm  obj
         *         The object to be compbred with this init mbx strebms
         *
         * @return  true if the specified object is bnother
         *          {@code InitMbxStrebms} instbnce with the sbme number of in
         *          bnd out bound strebms
         */
        @Override
        public boolebn equbls(Object obj) {
            if (obj != null && obj instbnceof InitMbxStrebms) {
                InitMbxStrebms thbt = (InitMbxStrebms) obj;
                if (this.mbxInStrebms == thbt.mbxInStrebms &&
                    this.mbxOutStrebms == thbt.mbxOutStrebms)
                    return true;
            }
            return fblse;
        }

        /**
         * Returns b hbsh code vblue for this init mbx strebms.
         */
        @Override
        public int hbshCode() {
            int hbsh = 7 ^ mbxInStrebms ^ mbxOutStrebms;
            return hbsh;
        }
    }
}
