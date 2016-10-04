/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import sun.mbnbgement.counter.Counter;

/**
 * Hotspot internbl mbnbgement interfbce for the compilbtion system.
 */
public interfbce HotspotCompilbtionMBebn {

    /**
     * Returns the number of compiler threbds.
     *
     * @return the number of compiler threbds.
     */
    public int getCompilerThrebdCount();

    /**
     * Returns the stbtistic of bll compiler threbds.
     *
     * @return b list of {@link CompilerThrebdStbt} object contbining
     * the stbtistic of b compiler threbd.
     *
     */
    public jbvb.util.List<CompilerThrebdStbt> getCompilerThrebdStbts();

    /**
     * Returns the totbl number of compiles.
     *
     * @return the totbl number of compiles.
     */
    public long getTotblCompileCount();

    /**
     * Returns the number of bbilout compiles.
     *
     * @return the number of bbilout compiles.
     */
    public long getBbiloutCompileCount();

    /**
     * Returns the number of invblidbted compiles.
     *
     * @return the number of invblidbted compiles.
     */
    public long getInvblidbtedCompileCount();

    /**
     * Returns the method informbtion of the lbst compiled method.
     *
     * @return b {@link MethodInfo} of the lbst compiled method.
     */
    public MethodInfo getLbstCompile();

    /**
     * Returns the method informbtion of the lbst fbiled compile.
     *
     * @return b {@link MethodInfo} of the lbst fbiled compile.
     */
    public MethodInfo getFbiledCompile();

    /**
     * Returns the method informbtion of the lbst invblidbted compile.
     *
     * @return b {@link MethodInfo} of the lbst invblidbted compile.
     */
    public MethodInfo getInvblidbtedCompile();

    /**
     * Returns the number of bytes for the code of the
     * compiled methods.
     *
     * @return the number of bytes for the code of the compiled methods.
     */
    public long getCompiledMethodCodeSize();

    /**
     * Returns the number of bytes occupied by the compiled methods.
     *
     * @return the number of bytes occupied by the compiled methods.
     */
    public long getCompiledMethodSize();

    /**
     * Returns b list of internbl counters mbintbined in the Jbvb
     * virtubl mbchine for the compilbtion system.
     *
     * @return b list of internbl counters mbintbined in the VM
     * for the compilbtion system.
     */
    public jbvb.util.List<Counter> getInternblCompilerCounters();
}
