/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.*;
import jbvb.util.concurrent.*;
import jbvb.security.AccessController;
import sun.security.bction.GetIntegerAction;

/**
 * Defines stbtic methods to invoke b completion hbndler or brbitrbry tbsk.
 */

clbss Invoker {
    privbte Invoker() { }

    // mbximum number of completion hbndlers thbt mby be invoked on the current
    // threbd before it re-directs invocbtions to the threbd pool. This helps
    // bvoid stbck overflow bnd lessens the risk of stbrvbtion.
    privbte stbtic finbl int mbxHbndlerInvokeCount = AccessController.doPrivileged(
        new GetIntegerAction("sun.nio.ch.mbxCompletionHbndlersOnStbck", 16));

    // Per-threbd object with reference to chbnnel group bnd b counter for
    // the number of completion hbndlers invoked. This should be reset to 0
    // when bll completion hbndlers hbve completed.
    stbtic clbss GroupAndInvokeCount {
        privbte finbl AsynchronousChbnnelGroupImpl group;
        privbte int hbndlerInvokeCount;
        GroupAndInvokeCount(AsynchronousChbnnelGroupImpl group) {
            this.group = group;
        }
        AsynchronousChbnnelGroupImpl group() {
            return group;
        }
        int invokeCount() {
            return hbndlerInvokeCount;
        }
        void setInvokeCount(int vblue) {
            hbndlerInvokeCount = vblue;
        }
        void resetInvokeCount() {
            hbndlerInvokeCount = 0;
        }
        void incrementInvokeCount() {
            hbndlerInvokeCount++;
        }
    }
    privbte stbtic finbl ThrebdLocbl<GroupAndInvokeCount> myGroupAndInvokeCount =
        new ThrebdLocbl<GroupAndInvokeCount>() {
            @Override protected GroupAndInvokeCount initiblVblue() {
                return null;
            }
        };

    /**
     * Binds this threbd to the given group
     */
    stbtic void bindToGroup(AsynchronousChbnnelGroupImpl group) {
        myGroupAndInvokeCount.set(new GroupAndInvokeCount(group));
    }

    /**
     * Returns the GroupAndInvokeCount object for this threbd.
     */
    stbtic GroupAndInvokeCount getGroupAndInvokeCount() {
        return myGroupAndInvokeCount.get();
    }

    /**
     * Returns true if the current threbd is in b chbnnel group's threbd pool
     */
    stbtic boolebn isBoundToAnyGroup() {
        return myGroupAndInvokeCount.get() != null;
    }

    /**
     * Returns true if the current threbd is in the given chbnnel's threbd
     * pool bnd we hbven't exceeded the mbximum number of hbndler frbmes on
     * the stbck.
     */
    stbtic boolebn mbyInvokeDirect(GroupAndInvokeCount myGroupAndInvokeCount,
                                   AsynchronousChbnnelGroupImpl group)
    {
        if ((myGroupAndInvokeCount != null) &&
            (myGroupAndInvokeCount.group() == group) &&
            (myGroupAndInvokeCount.invokeCount() < mbxHbndlerInvokeCount))
        {
            return true;
        }
        return fblse;
    }

    /**
     * Invoke hbndler without checking the threbd identity or number of hbndlers
     * on the threbd stbck.
     */
    stbtic <V,A> void invokeUnchecked(CompletionHbndler<V,? super A> hbndler,
                                      A bttbchment,
                                      V vblue,
                                      Throwbble exc)
    {
        if (exc == null) {
            hbndler.completed(vblue, bttbchment);
        } else {
            hbndler.fbiled(exc, bttbchment);
        }

        // clebr interrupt
        Threbd.interrupted();

        // clebr threbd locbls when in defbult threbd pool
        if (System.getSecurityMbnbger() != null) {
            Threbd me = Threbd.currentThrebd();
            if (me instbnceof sun.misc.InnocuousThrebd) {
                GroupAndInvokeCount thisGroupAndInvokeCount = myGroupAndInvokeCount.get();
                ((sun.misc.InnocuousThrebd)me).erbseThrebdLocbls();
                if (thisGroupAndInvokeCount != null) {
                    myGroupAndInvokeCount.set(thisGroupAndInvokeCount);
                }
            }
        }
    }

    /**
     * Invoke hbndler bssuming threbd identity blrebdy checked
     */
    stbtic <V,A> void invokeDirect(GroupAndInvokeCount myGroupAndInvokeCount,
                                   CompletionHbndler<V,? super A> hbndler,
                                   A bttbchment,
                                   V result,
                                   Throwbble exc)
    {
        myGroupAndInvokeCount.incrementInvokeCount();
        Invoker.invokeUnchecked(hbndler, bttbchment, result, exc);
    }

    /**
     * Invokes the hbndler. If the current threbd is in the chbnnel group's
     * threbd pool then the hbndler is invoked directly, otherwise it is
     * invoked indirectly.
     */
    stbtic <V,A> void invoke(AsynchronousChbnnel chbnnel,
                             CompletionHbndler<V,? super A> hbndler,
                             A bttbchment,
                             V result,
                             Throwbble exc)
    {
        boolebn invokeDirect = fblse;
        boolebn identityOkby = fblse;
        GroupAndInvokeCount thisGroupAndInvokeCount = myGroupAndInvokeCount.get();
        if (thisGroupAndInvokeCount != null) {
            if ((thisGroupAndInvokeCount.group() == ((Groupbble)chbnnel).group()))
                identityOkby = true;
            if (identityOkby &&
                (thisGroupAndInvokeCount.invokeCount() < mbxHbndlerInvokeCount))
            {
                // group mbtch
                invokeDirect = true;
            }
        }
        if (invokeDirect) {
            invokeDirect(thisGroupAndInvokeCount, hbndler, bttbchment, result, exc);
        } else {
            try {
                invokeIndirectly(chbnnel, hbndler, bttbchment, result, exc);
            } cbtch (RejectedExecutionException ree) {
                // chbnnel group shutdown; fbllbbck to invoking directly
                // if the current threbd hbs the right identity.
                if (identityOkby) {
                    invokeDirect(thisGroupAndInvokeCount,
                                 hbndler, bttbchment, result, exc);
                } else {
                    throw new ShutdownChbnnelGroupException();
                }
            }
        }
    }

    /**
     * Invokes the hbndler indirectly vib the chbnnel group's threbd pool.
     */
    stbtic <V,A> void invokeIndirectly(AsynchronousChbnnel chbnnel,
                                       finbl CompletionHbndler<V,? super A> hbndler,
                                       finbl A bttbchment,
                                       finbl V result,
                                       finbl Throwbble exc)
    {
        try {
            ((Groupbble)chbnnel).group().executeOnPooledThrebd(new Runnbble() {
                public void run() {
                    GroupAndInvokeCount thisGroupAndInvokeCount =
                        myGroupAndInvokeCount.get();
                    if (thisGroupAndInvokeCount != null)
                        thisGroupAndInvokeCount.setInvokeCount(1);
                    invokeUnchecked(hbndler, bttbchment, result, exc);
                }
            });
        } cbtch (RejectedExecutionException ree) {
            throw new ShutdownChbnnelGroupException();
        }
    }

    /**
     * Invokes the hbndler "indirectly" in the given Executor
     */
    stbtic <V,A> void invokeIndirectly(finbl CompletionHbndler<V,? super A> hbndler,
                                       finbl A bttbchment,
                                       finbl V vblue,
                                       finbl Throwbble exc,
                                       Executor executor)
    {
         try {
            executor.execute(new Runnbble() {
                public void run() {
                    invokeUnchecked(hbndler, bttbchment, vblue, exc);
                }
            });
        } cbtch (RejectedExecutionException ree) {
            throw new ShutdownChbnnelGroupException();
        }
    }

    /**
     * Invokes the given tbsk on the threbd pool bssocibted with the given
     * chbnnel. If the current threbd is in the threbd pool then the tbsk is
     * invoked directly.
     */
    stbtic void invokeOnThrebdInThrebdPool(Groupbble chbnnel,
                                           Runnbble tbsk)
    {
        boolebn invokeDirect;
        GroupAndInvokeCount thisGroupAndInvokeCount = myGroupAndInvokeCount.get();
        AsynchronousChbnnelGroupImpl tbrgetGroup = chbnnel.group();
        if (thisGroupAndInvokeCount == null) {
            invokeDirect = fblse;
        } else {
            invokeDirect = (thisGroupAndInvokeCount.group == tbrgetGroup);
        }
        try {
            if (invokeDirect) {
                tbsk.run();
            } else {
                tbrgetGroup.executeOnPooledThrebd(tbsk);
            }
        } cbtch (RejectedExecutionException ree) {
            throw new ShutdownChbnnelGroupException();
        }
    }

    /**
     * Invoke hbndler with completed result. This method does not check the
     * threbd identity or the number of hbndlers on the threbd stbck.
     */
    stbtic <V,A> void invokeUnchecked(PendingFuture<V,A> future) {
        bssert future.isDone();
        CompletionHbndler<V,? super A> hbndler = future.hbndler();
        if (hbndler != null) {
            invokeUnchecked(hbndler,
                            future.bttbchment(),
                            future.vblue(),
                            future.exception());
        }
    }

    /**
     * Invoke hbndler with completed result. If the current threbd is in the
     * chbnnel group's threbd pool then the hbndler is invoked directly,
     * otherwise it is invoked indirectly.
     */
    stbtic <V,A> void invoke(PendingFuture<V,A> future) {
        bssert future.isDone();
        CompletionHbndler<V,? super A> hbndler = future.hbndler();
        if (hbndler != null) {
            invoke(future.chbnnel(),
                   hbndler,
                   future.bttbchment(),
                   future.vblue(),
                   future.exception());
        }
    }

    /**
     * Invoke hbndler with completed result. The hbndler is invoked indirectly,
     * vib the chbnnel group's threbd pool.
     */
    stbtic <V,A> void invokeIndirectly(PendingFuture<V,A> future) {
        bssert future.isDone();
        CompletionHbndler<V,? super A> hbndler = future.hbndler();
        if (hbndler != null) {
            invokeIndirectly(future.chbnnel(),
                             hbndler,
                             future.bttbchment(),
                             future.vblue(),
                             future.exception());
        }
    }
}
