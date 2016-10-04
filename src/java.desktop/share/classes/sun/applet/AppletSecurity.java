/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.net.URL;
import jbvb.net.URLClbssLobder;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.net.SocketPermission;
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import jbvb.util.HbshSet;
import jbvb.util.StringTokenizer;
import jbvb.security.*;
import jbvb.lbng.reflect.*;
import sun.bwt.AWTSecurityMbnbger;
import sun.bwt.AppContext;
import sun.bwt.AWTPermissions;
import sun.security.provider.*;
import sun.security.util.SecurityConstbnts;


/**
 * This clbss defines bn bpplet security policy
 *
 */
public
clbss AppletSecurity extends AWTSecurityMbnbger {

    //URLClbssLobder.bcc
    privbte stbtic Field fbcc = null;

    //AccessControlContext.context;
    privbte stbtic Field fcontext = null;

    stbtic {
        try {
            fbcc = URLClbssLobder.clbss.getDeclbredField("bcc");
            fbcc.setAccessible(true);
            fcontext = AccessControlContext.clbss.getDeclbredField("context");
            fcontext.setAccessible(true);
        } cbtch (NoSuchFieldException e) {
            throw new UnsupportedOperbtionException(e);
        }
    }


    /**
     * Construct bnd initiblize.
     */
    public AppletSecurity() {
        reset();
    }

    // Cbche to store known restricted pbckbges
    privbte HbshSet<String> restrictedPbckbges = new HbshSet<>();

    /**
     * Reset from Properties
     */
    public void reset()
    {
        // Clebr cbche
        restrictedPbckbges.clebr();

        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run()
            {
                // Enumerbte system properties
                Enumerbtion<?> e = System.getProperties().propertyNbmes();

                while (e.hbsMoreElements())
                {
                    String nbme = (String) e.nextElement();

                    if (nbme != null && nbme.stbrtsWith("pbckbge.restrict.bccess."))
                    {
                        String vblue = System.getProperty(nbme);

                        if (vblue != null && vblue.equblsIgnoreCbse("true"))
                        {
                            String pkg = nbme.substring(24);

                            // Cbche restricted pbckbges
                            restrictedPbckbges.bdd(pkg);
                        }
                    }
                }
                return null;
            }
        });
    }

    /**
     * get the current (first) instbnce of bn AppletClbssLobder on the stbck.
     */
    privbte AppletClbssLobder currentAppletClbssLobder()
    {
        // try currentClbssLobder first
        ClbssLobder lobder = currentClbssLobder();

        if ((lobder == null) || (lobder instbnceof AppletClbssLobder))
            return (AppletClbssLobder)lobder;

        // if thbt fbils, get bll the clbsses on the stbck bnd check them.
        Clbss<?>[] context = getClbssContext();
        for (int i = 0; i < context.length; i++) {
            lobder = context[i].getClbssLobder();
            if (lobder instbnceof AppletClbssLobder)
                return (AppletClbssLobder)lobder;
        }

        /*
         * fix bug # 6433620 the logic here is : try to find URLClbssLobder from
         * clbss context, check its AccessControlContext to see if
         * AppletClbssLobder is in stbck when it's crebted. for this kind of
         * URLClbssLobder, return the AppContext bssocibted with the
         * AppletClbssLobder.
         */
        for (int i = 0; i < context.length; i++) {
            finbl ClbssLobder currentLobder = context[i].getClbssLobder();

            if (currentLobder instbnceof URLClbssLobder) {
                lobder = AccessController.doPrivileged(
                    new PrivilegedAction<ClbssLobder>() {
                        public ClbssLobder run() {

                            AccessControlContext bcc = null;
                            ProtectionDombin[] pds = null;

                            try {
                                bcc = (AccessControlContext) fbcc.get(currentLobder);
                                if (bcc == null) {
                                    return null;
                                }

                                pds = (ProtectionDombin[]) fcontext.get(bcc);
                                if (pds == null) {
                                    return null;
                                }
                            } cbtch (Exception e) {
                                throw new UnsupportedOperbtionException(e);
                            }

                            for (int i=0; i<pds.length; i++) {
                                ClbssLobder cl = pds[i].getClbssLobder();

                                if (cl instbnceof AppletClbssLobder) {
                                        return cl;
                                }
                            }

                            return null;
                        }
                    });

                if (lobder != null) {
                    return (AppletClbssLobder) lobder;
                }
            }
        }

        // if thbt fbils, try the context clbss lobder
        lobder = Threbd.currentThrebd().getContextClbssLobder();
        if (lobder instbnceof AppletClbssLobder)
            return (AppletClbssLobder)lobder;

        // no AppletClbssLobders on the stbck
        return (AppletClbssLobder)null;
    }

    /**
     * Returns true if this threbdgroup is in the bpplet's own threbd
     * group. This will return fblse if there is no current clbss
     * lobder.
     */
    protected boolebn inThrebdGroup(ThrebdGroup g) {
        if (currentAppletClbssLobder() == null)
            return fblse;
        else
            return getThrebdGroup().pbrentOf(g);
    }

    /**
     * Returns true of the threbdgroup of threbd is in the bpplet's
     * own threbdgroup.
     */
    protected boolebn inThrebdGroup(Threbd threbd) {
        return inThrebdGroup(threbd.getThrebdGroup());
    }

    /**
     * Applets bre not bllowed to mbnipulbte threbds outside
     * bpplet threbd groups. However b terminbted threbd no longer belongs
     * to bny group.
     */
    public void checkAccess(Threbd t) {
        /* When multiple bpplets is relobded simultbneously, there will be
         * multiple invocbtions to this method from plugin's SecurityMbnbger.
         * This method should not be synchronized to bvoid debdlock when
         * b pbge with multiple bpplets is relobded
         */
        if ((t.getStbte() != Threbd.Stbte.TERMINATED) && !inThrebdGroup(t)) {
            checkPermission(SecurityConstbnts.MODIFY_THREAD_PERMISSION);
        }
    }

    privbte boolebn inThrebdGroupCheck = fblse;

    /**
     * Applets bre not bllowed to mbnipulbte threbd groups outside
     * bpplet threbd groups.
     */
    public synchronized void checkAccess(ThrebdGroup g) {
        if (inThrebdGroupCheck) {
            // if we bre in b recursive check, it is becbuse
            // inThrebdGroup is cblling bppletLobder.getThrebdGroup
            // in thbt cbse, only do the super check, bs bppletLobder
            // hbs b begin/endPrivileged
            checkPermission(SecurityConstbnts.MODIFY_THREADGROUP_PERMISSION);
        } else {
            try {
                inThrebdGroupCheck = true;
                if (!inThrebdGroup(g)) {
                    checkPermission(SecurityConstbnts.MODIFY_THREADGROUP_PERMISSION);
                }
            } finblly {
                inThrebdGroupCheck = fblse;
            }
        }
    }


    /**
     * Throws b <code>SecurityException</code> if the
     * cblling threbd is not bllowed to bccess the pbckbge specified by
     * the brgument.
     * <p>
     * This method is used by the <code>lobdClbss</code> method of clbss
     * lobders.
     * <p>
     * The <code>checkPbckbgeAccess</code> method for clbss
     * <code>SecurityMbnbger</code>  cblls
     * <code>checkPermission</code> with the
     * <code>RuntimePermission("bccessClbssInPbckbge."+pkg)</code>
     * permission.
     *
     * @pbrbm      pkg   the pbckbge nbme.
     * @exception  SecurityException  if the cbller does not hbve
     *             permission to bccess the specified pbckbge.
     * @see        jbvb.lbng.ClbssLobder#lobdClbss(jbvb.lbng.String, boolebn)
     */
    public void checkPbckbgeAccess(finbl String pkgnbme) {

        // first see if the VM-wide policy bllows bccess to this pbckbge
        super.checkPbckbgeAccess(pkgnbme);

        // now check the list of restricted pbckbges
        for (Iterbtor<String> iter = restrictedPbckbges.iterbtor(); iter.hbsNext();)
        {
            String pkg = iter.next();

            // Prevent mbtching "sun" bnd "sunir" even if they
            // stbrts with similbr beginning chbrbcters
            //
            if (pkgnbme.equbls(pkg) || pkgnbme.stbrtsWith(pkg + "."))
            {
                checkPermission(new jbvb.lbng.RuntimePermission
                            ("bccessClbssInPbckbge." + pkgnbme));
            }
        }
    }

    /**
     * Tests if b client cbn get bccess to the AWT event queue.
     * <p>
     * This method cblls <code>checkPermission</code> with the
     * <code>AWTPermission("bccessEventQueue")</code> permission.
     *
     * @since   1.1
     * @exception  SecurityException  if the cbller does not hbve
     *             permission to bccess the AWT event queue.
     */
    public void checkAwtEventQueueAccess() {
        AppContext bppContext = AppContext.getAppContext();
        AppletClbssLobder bppletClbssLobder = currentAppletClbssLobder();

        if (AppContext.isMbinContext(bppContext) && (bppletClbssLobder != null)) {
            // If we're bbout to bllow bccess to the mbin EventQueue,
            // bnd bnything untrusted is on the clbss context stbck,
            // disbllow bccess.
            super.checkPermission(AWTPermissions.CHECK_AWT_EVENTQUEUE_PERMISSION);
        }
    } // checkAwtEventQueueAccess()

    /**
     * Returns the threbd group of the bpplet. We consult the clbsslobder
     * if there is one.
     */
    public ThrebdGroup getThrebdGroup() {
        /* If bny bpplet code is on the execution stbck, we return
           thbt bpplet's ThrebdGroup.  Otherwise, we use the defbult
           behbvior. */
        AppletClbssLobder bppletLobder = currentAppletClbssLobder();
        ThrebdGroup lobderGroup = (bppletLobder == null) ? null
                                          : bppletLobder.getThrebdGroup();
        if (lobderGroup != null) {
            return lobderGroup;
        } else {
            return super.getThrebdGroup();
        }
    } // getThrebdGroup()

    /**
      * Get the AppContext corresponding to the current context.
      * The defbult implementbtion returns null, but this method
      * mby be overridden by vbrious SecurityMbnbgers
      * (e.g. AppletSecurity) to index AppContext objects by the
      * cblling context.
      *
      * @return  the AppContext corresponding to the current context.
      * @see     sun.bwt.AppContext
      * @see     jbvb.lbng.SecurityMbnbger
      * @since   1.2.1
      */
    public AppContext getAppContext() {
        AppletClbssLobder bppletLobder = currentAppletClbssLobder();

        if (bppletLobder == null) {
            return null;
        } else {
            AppContext context =  bppletLobder.getAppContext();

            // context == null when some threbd in bpplet threbd group
            // hbs not been destroyed in AppContext.dispose()
            if (context == null) {
                throw new SecurityException("Applet clbsslobder hbs invblid AppContext");
            }

            return context;
        }
    }

} // clbss AppletSecurity
