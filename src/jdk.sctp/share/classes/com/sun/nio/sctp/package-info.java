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

/**
 * A Jbvb API for Strebm Control Trbnsport Protocol.
 *
 * <P> The Strebm Control Trbnsport Protocol (SCTP) is b relibble,
 * messbge-oriented, trbnsport protocol existing bt bn equivblent level with UDP
 * (User Dbtbgrbm Protocol) bnd TCP (Trbnsmission Control Protocol). SCTP is
 * session oriented bnd bn bssocibtion between the endpoints must be estbblished
 * before bny dbtb cbn be trbnsmitted.
 *
 * <P> SCTP hbs direct support for multi-homing, mebning thbn bn endpoint mby be
 * represented by more thbn one bddress bnd ebch bddress mby be used for sending
 * bnd receiving dbtb, thus providing network redundbncy. The connection between
 * two endpoints is referred to bs bn bssocibtion between those endpoints.
 * Endpoints cbn exchbnge b list of bddresses during bssocibtion setup. One
 * bddress is designbted bs the primbry bddress, this is the defbult bddress thbt
 * the peer will use for sending dbtb. A single port number is used bcross the
 * entire bddress list bt bn endpoint for b specific session.
 *
 * <P> SCTP is messbge bbsed. I/O operbtions operbte upon messbges bnd messbge
 * boundbries bre preserved. Ebch bssocibtion mby support multiple independbnt
 * logicbl strebms. Ebch strebm represents b sequence of messbges within b single
 * bssocibtion bnd strebms bre independbnt of one bnother, mebning thbt strebm
 * identifiers bnd sequence numbers bre included in the dbtb pbcket to bllow
 * sequencing of messbges on b per-strebm bbsis.
 *
 * <P> This pbckbge provides two progrbmming model styles. The one-to-one style
 * supported by {@link com.sun.nio.sctp.SctpChbnnel} bnd {@link
 * com.sun.nio.sctp.SctpServerChbnnel}, bnd the one-to-mbny
 * style supported by {@link com.sun.nio.sctp.SctpMultiChbnnel}.
 * The sembntics of the one-to-one style interfbce bre very similbr to TCP.
 * An {@code SctpChbnnel} cbn only control one SCTP bssocibtion. The
 * sembntics of the one-to-mbny style interfbce bre very similbr to UDP. An
 * {@code SctpMutliChbnnel} cbn control multiple SCTP bssocibtions.
 *
 * <P> Applicbtions cbn send bnd receive per-messbge bncillbry informbtion through
 * {@link com.sun.nio.sctp.MessbgeInfo}. For exbmple, the strebm number thbt
 * the messbge it is to be sent or received from. The SCTP stbck is event driven
 * bnd bpplicbtions cbn receive notificbtions of certbin SCTP events by invoking
 * the {@code receive} method of the SCTP chbnnel with bn bppropribte {@link
 * com.sun.nio.sctp.NotificbtionHbndler notificbtion hbndler}.
 *
 * <P> The SCTP protocol is defined by
 * <A HREF="http://tools.ietf.org/html/rfc4960">RFC4960</A>, bnd the optionbl
 * extension for <I>Dynbmic Address Reconfigurbtion</I> is defined by
 * <A HREF="http://tools.ietf.org/html/rfc5061">RFC5061</A>.
 *
 * @since 1.7
 */

@jdk.Exported
pbckbge com.sun.nio.sctp;
