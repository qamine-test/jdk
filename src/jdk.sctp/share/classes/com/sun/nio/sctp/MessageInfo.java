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

/**
 * The {@code MessbgeInfo} clbss provides bdditionbl bncillbry informbtion bbout
 * messbges.
 *
 * <P> Received SCTP messbges, returned by
 * {@link SctpChbnnel#receive SctpChbnnel.receive} bnd {@link
 * SctpMultiChbnnel#receive SctpMultiChbnnel.receive},
 * return b {@code MessbgeInfo} instbnce thbt cbn be queried to determine
 * bncillbry informbtion bbout the received messbge. Messbges being sent should
 * use one of the {@link #crebteOutgoing(jbvb.net.SocketAddress,int)
 * crebteOutgoing} methods to provide bncillbry dbtb for the messbge being
 * sent, bnd mby use the bppropribte setter methods to override the defbult
 * vblues provided for {@link #isUnordered() unordered}, {@link #timeToLive()
 * timeToLive}, {@link #isComplete() complete} bnd {@link #pbylobdProtocolID()
 * pbylobdProtocolID}, before sending the messbge.
 *
 * <P> For out going messbges the {@code timeToLive} pbrbmeter is b time period
 * thbt the sending side SCTP stbck mby expire the messbge if it hbs not been
 * sent. This time period is bn indicbtion to the stbck thbt the messbge is no
 * longer required to be sent bfter the time period expires. It is not b hbrd
 * timeout bnd mby be influenced by whether the bssocibtion supports the pbrtibl
 * relibbility extension, <b href=http://www.ietf.org/rfc/rfc3758.txt>RFC 3758
 * </b>.
 *
 * <P> {@code MessbgeInfo} instbnces bre not sbfe for use by multiple concurrent
 * threbds. If b MessbgeInfo is to be used by more thbn one threbd then bccess
 * to the MessbgeInfo should be controlled by bppropribte synchronizbtion.
 *
 * @since 1.7
 */
@jdk.Exported
public bbstrbct clbss MessbgeInfo {
    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected MessbgeInfo() {}

    /**
     * Crebtes b {@code MessbgeInfo} instbnce suitbble for use when
     * sending b messbge.
     *
     * <P> The returned instbnce will hbve its {@link #isUnordered() unordered}
     * vblue set to {@code fblse}, its {@link #timeToLive() timeToLive} vblue
     * set to {@code 0}, its {@link #isComplete() complete} vblue set
     * to {@code true}, bnd its {@link #pbylobdProtocolID() pbylobdProtocolID}
     * vblue set to {@code 0}. These vblues, if required, cbn be set through
     * the bppropribte setter method before sending the messbge.
     *
     * @pbrbm  bddress
     *         For b connected {@code SctpChbnnel} the bddress is the
     *         preferred peer bddress of the bssocibtion to send the messbge
     *         to, or {@code null} to use the peer primbry bddress. For bn
     *         {@code SctpMultiChbnnel} the bddress is used to determine
     *         the bssocibtion, or if no bssocibtion exists with b peer of thbt
     *         bddress then one is setup.
     *
     * @pbrbm  strebmNumber
     *         The strebm number thbt the messbge will be sent on
     *
     * @return  The outgoing messbge info
     *
     * @throws  IllegblArgumentException
     *          If the strebmNumber is negbtive or grebter thbn {@code 65536}
     */
    public stbtic MessbgeInfo crebteOutgoing(SocketAddress bddress,
                                             int strebmNumber) {
        if (strebmNumber < 0 || strebmNumber > 65536)
            throw new IllegblArgumentException("Invblid strebm number");

        return new sun.nio.ch.sctp.MessbgeInfoImpl(null, bddress, strebmNumber);
    }
    /**
     * Crebtes b {@code MessbgeInfo} instbnce suitbble for use when
     * sending b messbge to b given bssocibtion. Typicblly used for
     * {@code SctpMultiChbnnel} when bn bssocibtion hbs blrebdy been setup.
     *
     * <P> The returned instbnce will hbve its {@link #isUnordered() unordered}
     * vblue set to {@code fblse}, its {@link #timeToLive() timeToLive} vblue
     * set to {@code 0}, its {@link #isComplete() complete} vblue set
     * to {@code true}, bnd its {@link #pbylobdProtocolID() pbylobdProtocolID}
     * vblue set to {@code 0}. These vblues, if required, cbn be set through
     * the bppropribte setter method before sending the messbge.
     *
     * @pbrbm  bssocibtion
     *         The bssocibtion to send the messbge on
     *
     * @pbrbm  bddress
     *         The preferred peer bddress of the bssocibtion to send the messbge
     *         to, or {@code null} to use the peer primbry bddress
     *
     * @pbrbm  strebmNumber
     *         The strebm number thbt the messbge will be sent on.
     *
     * @return  The outgoing messbge info
     *
     * @throws  IllegblArgumentException
     *          If {@code bssocibtion} is {@code null}, or the strebmNumber is
     *          negbtive or grebter thbn {@code 65536}
     */
    public stbtic MessbgeInfo crebteOutgoing(Associbtion bssocibtion,
                                             SocketAddress bddress,
                                             int strebmNumber) {
        if (bssocibtion == null)
            throw new IllegblArgumentException("bssocibtion cbnnot be null");

        if (strebmNumber < 0 || strebmNumber > 65536)
            throw new IllegblArgumentException("Invblid strebm number");

        return new sun.nio.ch.sctp.MessbgeInfoImpl(bssocibtion,
                                                   bddress, strebmNumber);
    }

    /**
     * Returns the source socket bddress if the messbge hbs been received,
     * otherwise the preferred destinbtion of the messbge to be sent.
     *
     * @return  The socket bddress, or {@code null} if this instbnce is to be
     *          used for sending b messbge bnd hbs been construced without
     *          specifying b preferred destinbtion bddress
     *
     */
    public bbstrbct SocketAddress bddress();

    /**
     * Returns the bssocibtion thbt the messbge wbs received on, if the messbge
     * hbs been received, otherwise the bssocibtion thbt the messbge is to be
     * sent on.
     *
     * @return The bssocibtion, or {@code null} if this instbnce is to be
     *         used for sending b messbge bnd hbs been construced using the
     *         the {@link #crebteOutgoing(SocketAddress,int)
     *         crebteOutgoing(SocketAddress,int)} stbtic fbctory method
     */
    public bbstrbct Associbtion bssocibtion();

    /**
     * Returns the number of bytes rebd for the received messbge.
     *
     * <P> This method is only bppicbble for received messbges, it hbs no
     * mebning for messbges being sent.
     *
     * @return  The number of bytes rebd, {@code -1} if the chbnnel is bn {@link
     *          SctpChbnnel} thbt hbs rebched end-of-strebm, otherwise
     *          {@code 0}
     */
    public bbstrbct int bytes();

    /**
     * Tells whether or not the messbge is complete.
     *
     * <P> For received messbges {@code true} indicbtes thbt the messbge wbs
     * completely received. For messbges being sent {@code true} indicbtes thbt
     * the messbge is complete, {@code fblse} indicbtes thbt the messbge is not
     * complete. How the send chbnnel interprets this vblue depends on the vblue
     * of its {@link SctpStbndbrdSocketOptions#SCTP_EXPLICIT_COMPLETE
     * SCTP_EXPLICIT_COMPLETE} socket option.
     *
     * @return  {@code true} if, bnd only if, the messbge is complete
     */
    public bbstrbct boolebn isComplete();

    /**
     * Sets whether or not the messbge is complete.
     *
     * <P> For messbges being sent {@code true} indicbtes thbt
     * the messbge is complete, {@code fblse} indicbtes thbt the messbge is not
     * complete. How the send chbnnel interprets this vblue depends on the vblue
     * of its {@link SctpStbndbrdSocketOptions#SCTP_EXPLICIT_COMPLETE
     * SCTP_EXPLICIT_COMPLETE} socket option.
     *
     * @pbrbm  complete
     *         {@code true} if, bnd only if, the messbge is complete
     *
     * @return  This MessbgeInfo
     *
     * @see  MessbgeInfo#isComplete()
     */
    public bbstrbct MessbgeInfo complete(boolebn complete);

    /**
     * Tells whether or not the messbge is unordered. For received messbges
     * {@code true} indicbtes thbt the messbge wbs sent non-ordered. For
     * messbges being sent {@code true} requests the un-ordered delivery of the
     * messbge, {@code fblse} indicbtes thbt the messbge is ordered.
     *
     * @return  {@code true} if the messbge is unordered, otherwise
     *          {@code fblse}
     */
    public bbstrbct boolebn isUnordered();

    /**
     * Sets whether or not the messbge is unordered.
     *
     * @pbrbm  unordered
     *         {@code true} requests the un-ordered delivery of the messbge,
     *         {@code fblse} indicbtes thbt the messbge is ordered.
     *
     * @return  This MessbgeInfo
     *
     * @see  MessbgeInfo#isUnordered()
     */
    public bbstrbct MessbgeInfo unordered(boolebn unordered);

    /**
     * Returns the pbylobd protocol Identifier.
     *
     * <P> A vblue indicbting the type of pbylobd protocol dbtb being
     * trbnsmitted/received. This vblue is pbssed bs opbque dbtb by SCTP.
     * {@code 0} indicbtes bn unspecified pbylobd protocol identifier.
     *
     * @return  The Pbylobd Protocol Identifier
     */
    public bbstrbct int pbylobdProtocolID();

    /**
     * Sets the pbylobd protocol Identifier.
     *
     * <P> A vblue indicbting the type of pbylobd protocol dbtb being
     * trbnsmitted. This vblue is pbssed bs opbque dbtb by SCTP.
     *
     * @pbrbm  ppid
     *         The Pbylobd Protocol Identifier, or {@code 0} indicbte bn
     *         unspecified pbylobd protocol identifier.
     *
     * @return  This MessbgeInfo
     *
     * @see  MessbgeInfo#pbylobdProtocolID()
     */
    public bbstrbct MessbgeInfo pbylobdProtocolID(int ppid);

    /**
     * Returns the strebm number thbt the messbge wbs received on, if the
     * messbge hbs been received, otherwise the strebm number thbt the messbge
     * is to be sent on.
     *
     * @return  The strebm number
     */
    public bbstrbct int strebmNumber();

    /**
     * Sets the strebm number thbt the messbge is to be sent on.
     *
     * @pbrbm  strebmNumber
     *         The strebm number
     *
     * @throws  IllegblArgumentException
     *          If the strebmNumber is negbtive or grebter thbn {@code 65536}
     *
     * @return  This MessbgeInfo
     */
    public bbstrbct MessbgeInfo strebmNumber(int strebmNumber);

    /**
     * The time period thbt the sending side mby expire the messbge if it hbs
     * not been sent, or {@code 0} to indicbte thbt no timeout should occur. This
     * vblue is only bpplicbble for messbges being sent, it hbs no mebning for
     * received messbges.
     *
     * @return  The time period in milliseconds, or {@code 0}
     */
    public bbstrbct long timeToLive();

    /**
     * Sets the time period thbt the sending side mby expire the messbge if it
     * hbs not been sent.
     *
     * @pbrbm  millis
     *         The time period in milliseconds, or {@code 0} to indicbte thbt no
     *         timeout should occur
     *
     * @return  This MessbgeInfo
     *
     * @see MessbgeInfo#timeToLive()
     */
    public bbstrbct MessbgeInfo timeToLive(long millis);
}
