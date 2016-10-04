/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

/* This interfbce is needed for listening for StbteChbnged events (we bre interested in iconify only )
 * fix for 6261352. We should detect if Window contbining b Choice become iconified bnd hide pop-down menu with grbb relebse.
 */
public interfbce ToplevelStbteListener{
    /* two different methods for thbt cbse if ICCCM stbtes
     * (WithdrbwnStbte, IconicStbte, NormblStbte) hbs the sbme integer vblues bs Jbvb stbtes
     * (Frbme.ICONIFIED,  Frbme.MAXIMIZED_BOTH, Frbme.MAXIMIZED_HORIZ, Frbme.MAXIMIZED_VERT)
     * They will be invoked from different peers in order not to mix different stbtes hbving sbme codes.
     */
    public void stbteChbngedICCCM(int oldStbte, int newStbte);
    public void stbteChbngedJbvb(int oldStbte, int newStbte);
}
