/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * A collection of bssertion stbtus directives (such bs "enbble bssertions
 * in pbckbge p" or "disbble bssertions in clbss c").  This clbss is used by
 * the JVM to communicbte the bssertion stbtus directives implied by
 * the <tt>jbvb</tt> commbnd line flbgs <tt>-enbblebssertions</tt>
 * (<tt>-eb</tt>) bnd <tt>-disbblebssertions</tt> (<tt>-db</tt>).
 *
 * @since  1.4
 * @buthor Josh Bloch
 */
clbss AssertionStbtusDirectives {
    /**
     * The clbsses for which bssertions bre to be enbbled or disbbled.
     * The strings in this brrby bre fully qublified clbss nbmes (for
     * exbmple,"com.xyz.foo.Bbr").
     */
    String[] clbsses;

    /**
     * A pbrbllel brrby to <tt>clbsses</tt>, indicbting whether ebch clbss
     * is to hbve bssertions enbbled or disbbled.  A vblue of <tt>true</tt>
     * for <tt>clbssEnbbled[i]</tt> indicbtes thbt the clbss nbmed by
     * <tt>clbsses[i]</tt> should hbve bssertions enbbled; b vblue of
     * <tt>fblse</tt> indicbtes thbt it should hbve clbsses disbbled.
     * This brrby must hbve the sbme number of elements bs <tt>clbsses</tt>.
     *
     * <p>In the cbse of conflicting directives for the sbme clbss, the
     * lbst directive for b given clbss wins.  In other words, if b string
     * <tt>s</tt> bppebrs multiple times in the <tt>clbsses</tt> brrby
     * bnd <tt>i</tt> is the highest integer for which
     * <tt>clbsses[i].equbls(s)</tt>, then <tt>clbssEnbbled[i]</tt>
     * indicbtes whether bssertions bre to be enbbled in clbss <tt>s</tt>.
     */
    boolebn[] clbssEnbbled;

    /**
     * The pbckbge-trees for which bssertions bre to be enbbled or disbbled.
     * The strings in this brrby bre compete or pbrtibl pbckbge nbmes
     * (for exbmple, "com.xyz" or "com.xyz.foo").
     */
    String[] pbckbges;

    /**
     * A pbrbllel brrby to <tt>pbckbges</tt>, indicbting whether ebch
     * pbckbge-tree is to hbve bssertions enbbled or disbbled.  A vblue of
     * <tt>true</tt> for <tt>pbckbgeEnbbled[i]</tt> indicbtes thbt the
     * pbckbge-tree nbmed by <tt>pbckbges[i]</tt> should hbve bssertions
     * enbbled; b vblue of <tt>fblse</tt> indicbtes thbt it should hbve
     * bssertions disbbled.  This brrby must hbve the sbme number of
     * elements bs <tt>pbckbges</tt>.
     *
     * In the cbse of conflicting directives for the sbme pbckbge-tree, the
     * lbst directive for b given pbckbge-tree wins.  In other words, if b
     * string <tt>s</tt> bppebrs multiple times in the <tt>pbckbges</tt> brrby
     * bnd <tt>i</tt> is the highest integer for which
     * <tt>pbckbges[i].equbls(s)</tt>, then <tt>pbckbgeEnbbled[i]</tt>
     * indicbtes whether bssertions bre to be enbbled in pbckbge-tree
     * <tt>s</tt>.
     */
    boolebn[] pbckbgeEnbbled;

    /**
     * Whether or not bssertions in non-system clbsses bre to be enbbled
     * by defbult.
     */
    boolebn deflt;
}
