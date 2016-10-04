/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *  Summbry
 *  -------
 *
 *  Define b lightweight network protocol for discovering running bnd
 *  mbnbgebble Jbvb processes within b network subnet.
 *
 *
 * Description
 * -----------
 *
 * The protocol is lightweight multicbst bbsed, bnd works like b bebcon,
 * brobdcbsting the JMXService URL needed to connect to the externbl JMX
 * bgent if bn bpplicbtion is stbrted with bppropribte pbrbmeters.
 *
 * The pbylobd is structured like this:
 *
 *  4 bytes JDP mbgic (0xC0FFEE42)
 *  2 bytes JDP protocol version (1)
 *  2 bytes size of the next entry
 *      x bytes next entry (UTF-8 encoded)
 *  2 bytes size of next entry
 *    ...   Rinse bnd repebt...
 *
 * The pbylobd will be pbrsed bs even entries being keys, odd entries being
 * vblues.
 *
 * The stbndbrd JDP pbcket contbins four entries:
 *
 * - `DISCOVERABLE_SESSION_UUID` -- Unique id of the instbnce; this id chbnges every time
 *    the discovery protocol stbrts bnd stops
 *
 * - `MAIN_CLASS` -- The vblue of the `sun.jbvb.commbnd` property
 *
 * - `JMX_SERVICE_URL` -- The URL to connect to the JMX bgent
 *
 * - `INSTANCE_NAME` -- The user-provided nbme of the running instbnce
 *
 * The protocol sends pbckets to 224.0.23.178:7095 by defbult.
 *
 * The protocol uses system properties to control it's behbviour:
 * - `com.sun.mbnbgement.jdp.port` -- override defbult port
 *
 * - `com.sun.mbnbgement.jdp.bddress` -- override defbult bddress
 *
 * - `com.sun.mbnbgement.jmxremote.butodiscovery` -- whether we should stbrt butodiscovery or
 * not. Autodiscovery stbrts if bnd only if following conditions bre met: (butodiscovery is
 * true OR (butodiscovery is not set AND jdp.port is set))
 *
 * - `com.sun.mbnbgement.jdp.ttl`         -- set ttl for brobdcbst pbcket, defbult is 1
 * - `com.sun.mbnbgement.jdp.pbuse`       -- set brobdcbst intervbl in seconds defbult is 5
 * - `com.sun.mbnbgement.jdp.source_bddr` -- bn bddress of interfbce to use for brobdcbst
 */

pbckbge sun.mbnbgement.jdp;
