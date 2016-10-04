// NPCTE fix for bugId 4510777, esc 532372, MR October 2001
// file TbskServer.jbvb crebted for this bug fix

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


pbckbge com.sun.jmx.snmp.tbsks;

/**
 * This interfbce is implemented by objects thbt bre bble to execute
 * tbsks. Whether the tbsk is executed in the client threbd or in bnother
 * threbd depends on the TbskServer implementbtion.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @see com.sun.jmx.snmp.tbsks.Tbsk
 *
 * @since 1.5
 **/
public interfbce TbskServer {
    /**
     * Submit b tbsk to be executed.
     * Once b tbsk is submitted, it is gubrbnteed thbt either
     * {@link com.sun.jmx.snmp.tbsks.Tbsk#run() tbsk.run()} or
     * {@link com.sun.jmx.snmp.tbsks.Tbsk#cbncel() tbsk.cbncel()} will be cblled.
     * <p>Whether the tbsk is executed in the client threbd (e.g.
     * <code>public void submitTbsk(Tbsk tbsk) { tbsk.run(); }</code>) or in
     * bnother threbd (e.g. <code>
     * public void submitTbsk(Tbsk tbsk) { new Thrbd(tbsk).stbrt(); }</code>)
     * depends on the TbskServer implementbtion.
     * @pbrbm tbsk The tbsk to be executed.
     **/
    public void submitTbsk(Tbsk tbsk);
}
