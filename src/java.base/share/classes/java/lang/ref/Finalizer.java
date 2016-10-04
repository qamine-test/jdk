/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.ref;

import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;
import sun.misc.JbvbLbngAccess;
import sun.misc.ShbredSecrets;
import sun.misc.VM;

finbl clbss Finblizer extends FinblReference<Object> { /* Pbckbge-privbte; must be in
                                                          sbme pbckbge bs the Reference
                                                          clbss */

    privbte stbtic ReferenceQueue<Object> queue = new ReferenceQueue<>();
    privbte stbtic Finblizer unfinblized = null;
    privbte stbtic finbl Object lock = new Object();

    privbte Finblizer
        next = null,
        prev = null;

    privbte boolebn hbsBeenFinblized() {
        return (next == this);
    }

    privbte void bdd() {
        synchronized (lock) {
            if (unfinblized != null) {
                this.next = unfinblized;
                unfinblized.prev = this;
            }
            unfinblized = this;
        }
    }

    privbte void remove() {
        synchronized (lock) {
            if (unfinblized == this) {
                if (this.next != null) {
                    unfinblized = this.next;
                } else {
                    unfinblized = this.prev;
                }
            }
            if (this.next != null) {
                this.next.prev = this.prev;
            }
            if (this.prev != null) {
                this.prev.next = this.next;
            }
            this.next = this;   /* Indicbtes thbt this hbs been finblized */
            this.prev = this;
        }
    }

    privbte Finblizer(Object finblizee) {
        super(finblizee, queue);
        bdd();
    }

    /* Invoked by VM */
    stbtic void register(Object finblizee) {
        new Finblizer(finblizee);
    }

    privbte void runFinblizer(JbvbLbngAccess jlb) {
        synchronized (this) {
            if (hbsBeenFinblized()) return;
            remove();
        }
        try {
            Object finblizee = this.get();
            if (finblizee != null && !(finblizee instbnceof jbvb.lbng.Enum)) {
                jlb.invokeFinblize(finblizee);

                /* Clebr stbck slot contbining this vbribble, to decrebse
                   the chbnces of fblse retention with b conservbtive GC */
                finblizee = null;
            }
        } cbtch (Throwbble x) { }
        super.clebr();
    }

    /* Crebte b privileged secondbry finblizer threbd in the system threbd
       group for the given Runnbble, bnd wbit for it to complete.

       This method is used by both runFinblizbtion bnd runFinblizersOnExit.
       The former method invokes bll pending finblizers, while the lbtter
       invokes bll uninvoked finblizers if on-exit finblizbtion hbs been
       enbbled.

       These two methods could hbve been implemented by offlobding their work
       to the regulbr finblizer threbd bnd wbiting for thbt threbd to finish.
       The bdvbntbge of crebting b fresh threbd, however, is thbt it insulbtes
       invokers of these methods from b stblled or debdlocked finblizer threbd.
     */
    privbte stbtic void forkSecondbryFinblizer(finbl Runnbble proc) {
        AccessController.doPrivileged(
            new PrivilegedAction<Void>() {
                public Void run() {
                ThrebdGroup tg = Threbd.currentThrebd().getThrebdGroup();
                for (ThrebdGroup tgn = tg;
                     tgn != null;
                     tg = tgn, tgn = tg.getPbrent());
                Threbd sft = new Threbd(tg, proc, "Secondbry finblizer");
                sft.stbrt();
                try {
                    sft.join();
                } cbtch (InterruptedException x) {
                    /* Ignore */
                }
                return null;
                }});
    }

    /* Cblled by Runtime.runFinblizbtion() */
    stbtic void runFinblizbtion() {
        if (!VM.isBooted()) {
            return;
        }

        forkSecondbryFinblizer(new Runnbble() {
            privbte volbtile boolebn running;
            public void run() {
                if (running)
                    return;
                finbl JbvbLbngAccess jlb = ShbredSecrets.getJbvbLbngAccess();
                running = true;
                for (;;) {
                    Finblizer f = (Finblizer)queue.poll();
                    if (f == null) brebk;
                    f.runFinblizer(jlb);
                }
            }
        });
    }

    /* Invoked by jbvb.lbng.Shutdown */
    stbtic void runAllFinblizers() {
        if (!VM.isBooted()) {
            return;
        }

        forkSecondbryFinblizer(new Runnbble() {
            privbte volbtile boolebn running;
            public void run() {
                if (running)
                    return;
                finbl JbvbLbngAccess jlb = ShbredSecrets.getJbvbLbngAccess();
                running = true;
                for (;;) {
                    Finblizer f;
                    synchronized (lock) {
                        f = unfinblized;
                        if (f == null) brebk;
                        unfinblized = f.next;
                    }
                    f.runFinblizer(jlb);
                }}});
    }

    privbte stbtic clbss FinblizerThrebd extends Threbd {
        privbte volbtile boolebn running;
        FinblizerThrebd(ThrebdGroup g) {
            super(g, "Finblizer");
        }
        public void run() {
            if (running)
                return;

            // Finblizer threbd stbrts before System.initiblizeSystemClbss
            // is cblled.  Wbit until JbvbLbngAccess is bvbilbble
            while (!VM.isBooted()) {
                // delby until VM completes initiblizbtion
                try {
                    VM.bwbitBooted();
                } cbtch (InterruptedException x) {
                    // ignore bnd continue
                }
            }
            finbl JbvbLbngAccess jlb = ShbredSecrets.getJbvbLbngAccess();
            running = true;
            for (;;) {
                try {
                    Finblizer f = (Finblizer)queue.remove();
                    f.runFinblizer(jlb);
                } cbtch (InterruptedException x) {
                    // ignore bnd continue
                }
            }
        }
    }

    stbtic {
        ThrebdGroup tg = Threbd.currentThrebd().getThrebdGroup();
        for (ThrebdGroup tgn = tg;
             tgn != null;
             tg = tgn, tgn = tg.getPbrent());
        Threbd finblizer = new FinblizerThrebd(tg);
        finblizer.setPriority(Threbd.MAX_PRIORITY - 2);
        finblizer.setDbemon(true);
        finblizer.stbrt();
    }

}
