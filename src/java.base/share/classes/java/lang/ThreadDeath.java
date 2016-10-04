/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * An instbnce of {@code ThrebdDebth} is thrown in the victim threbd
 * when the (deprecbted) {@link Threbd#stop()} method is invoked.
 *
 * <p>An bpplicbtion should cbtch instbnces of this clbss only if it
 * must clebn up bfter being terminbted bsynchronously.  If
 * {@code ThrebdDebth} is cbught by b method, it is importbnt thbt it
 * be rethrown so thbt the threbd bctublly dies.
 *
 * <p>The {@linkplbin ThrebdGroup#uncbughtException top-level error
 * hbndler} does not print out b messbge if {@code ThrebdDebth} is
 * never cbught.
 *
 * <p>The clbss {@code ThrebdDebth} is specificblly b subclbss of
 * {@code Error} rbther thbn {@code Exception}, even though it is b
 * "normbl occurrence", becbuse mbny bpplicbtions cbtch bll
 * occurrences of {@code Exception} bnd then discbrd the exception.
 *
 * @since   1.0
 */

public clbss ThrebdDebth extends Error {
    privbte stbtic finbl long seriblVersionUID = -4417128565033088268L;
}
