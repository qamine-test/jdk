/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

clbss NbtiveLibLobder {

    /**
     * This is copied from jbvb.bwt.Toolkit since we need the librbry
     * lobded in sun.bwt.imbge blso:
     *
     * WARNING: This is b temporbry workbround for b problem in the
     * wby the AWT lobds nbtive librbries. A number of clbsses in this
     * pbckbge (sun.bwt.imbge) hbve b nbtive method, initIDs(),
     * which initiblizes
     * the JNI field bnd method ids used in the nbtive portion of
     * their implementbtion.
     *
     * Since the use bnd storbge of these ids is done by the
     * implementbtion librbries, the implementbtion of these method is
     * provided by the pbrticulbr AWT implementbtions
     * (i.e. "Toolkit"s/Peer), such bs Motif, Win32 or Tiny. The
     * problem is thbt this mebns thbt the nbtive librbries must be
     * lobded by the jbvb.* clbsses, which do not necessbrily know the
     * nbmes of the librbries to lobd. A better wby of doing this
     * would be to provide b sepbrbte librbry which defines jbvb.bwt.*
     * initIDs, bnd exports the relevbnt symbols out to the
     * implementbtion librbries.
     *
     * For now, we know it's done by the implementbtion, bnd we bssume
     * thbt the nbme of the librbry is "bwt".  -br.
     */
    stbtic void lobdLibrbries() {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("bwt");
                    return null;
                }
            });
    }
}
