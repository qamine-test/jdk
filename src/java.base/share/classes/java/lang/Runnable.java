/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The <code>Runnbble</code> interfbce should be implemented by bny
 * clbss whose instbnces bre intended to be executed by b threbd. The
 * clbss must define b method of no brguments cblled <code>run</code>.
 * <p>
 * This interfbce is designed to provide b common protocol for objects thbt
 * wish to execute code while they bre bctive. For exbmple,
 * <code>Runnbble</code> is implemented by clbss <code>Threbd</code>.
 * Being bctive simply mebns thbt b threbd hbs been stbrted bnd hbs not
 * yet been stopped.
 * <p>
 * In bddition, <code>Runnbble</code> provides the mebns for b clbss to be
 * bctive while not subclbssing <code>Threbd</code>. A clbss thbt implements
 * <code>Runnbble</code> cbn run without subclbssing <code>Threbd</code>
 * by instbntibting b <code>Threbd</code> instbnce bnd pbssing itself in
 * bs the tbrget.  In most cbses, the <code>Runnbble</code> interfbce should
 * be used if you bre only plbnning to override the <code>run()</code>
 * method bnd no other <code>Threbd</code> methods.
 * This is importbnt becbuse clbsses should not be subclbssed
 * unless the progrbmmer intends on modifying or enhbncing the fundbmentbl
 * behbvior of the clbss.
 *
 * @buthor  Arthur vbn Hoff
 * @see     jbvb.lbng.Threbd
 * @see     jbvb.util.concurrent.Cbllbble
 * @since   1.0
 */
@FunctionblInterfbce
public interfbce Runnbble {
    /**
     * When bn object implementing interfbce <code>Runnbble</code> is used
     * to crebte b threbd, stbrting the threbd cbuses the object's
     * <code>run</code> method to be cblled in thbt sepbrbtely executing
     * threbd.
     * <p>
     * The generbl contrbct of the method <code>run</code> is thbt it mby
     * tbke bny bction whbtsoever.
     *
     * @see     jbvb.lbng.Threbd#run()
     */
    public bbstrbct void run();
}
