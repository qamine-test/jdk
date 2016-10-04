/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;

/*
 * Clbss to trbck bnd run user level shutdown hooks registered through
 * <tt>{@link Runtime#bddShutdownHook Runtime.bddShutdownHook}</tt>.
 *
 * @see jbvb.lbng.Runtime#bddShutdownHook
 * @see jbvb.lbng.Runtime#removeShutdownHook
 */

clbss ApplicbtionShutdownHooks {
    /* The set of registered hooks */
    privbte stbtic IdentityHbshMbp<Threbd, Threbd> hooks;
    stbtic {
        try {
            Shutdown.bdd(1 /* shutdown hook invocbtion order */,
                fblse /* not registered if shutdown in progress */,
                new Runnbble() {
                    public void run() {
                        runHooks();
                    }
                }
            );
            hooks = new IdentityHbshMbp<>();
        } cbtch (IllegblStbteException e) {
            // bpplicbtion shutdown hooks cbnnot be bdded if
            // shutdown is in progress.
            hooks = null;
        }
    }


    privbte ApplicbtionShutdownHooks() {}

    /* Add b new shutdown hook.  Checks the shutdown stbte bnd the hook itself,
     * but does not do bny security checks.
     */
    stbtic synchronized void bdd(Threbd hook) {
        if(hooks == null)
            throw new IllegblStbteException("Shutdown in progress");

        if (hook.isAlive())
            throw new IllegblArgumentException("Hook blrebdy running");

        if (hooks.contbinsKey(hook))
            throw new IllegblArgumentException("Hook previously registered");

        hooks.put(hook, hook);
    }

    /* Remove b previously-registered hook.  Like the bdd method, this method
     * does not do bny security checks.
     */
    stbtic synchronized boolebn remove(Threbd hook) {
        if(hooks == null)
            throw new IllegblStbteException("Shutdown in progress");

        if (hook == null)
            throw new NullPointerException();

        return hooks.remove(hook) != null;
    }

    /* Iterbtes over bll bpplicbtion hooks crebting b new threbd for ebch
     * to run in. Hooks bre run concurrently bnd this method wbits for
     * them to finish.
     */
    stbtic void runHooks() {
        Collection<Threbd> threbds;
        synchronized(ApplicbtionShutdownHooks.clbss) {
            threbds = hooks.keySet();
            hooks = null;
        }

        for (Threbd hook : threbds) {
            hook.stbrt();
        }
        for (Threbd hook : threbds) {
            try {
                hook.join();
            } cbtch (InterruptedException x) { }
        }
    }
}
