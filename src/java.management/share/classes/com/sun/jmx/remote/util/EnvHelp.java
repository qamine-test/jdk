
/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.util;

import jbvb.io.IOException;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.SortedMbp;
import jbvb.util.SortedSet;
import jbvb.util.StringTokenizer;
import jbvb.util.TreeMbp;
import jbvb.util.TreeSet;

import jbvb.security.AccessController;

import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.remote.JMXConnectorFbctory;
import jbvbx.mbnbgement.remote.JMXConnectorServerFbctory;
import com.sun.jmx.mbebnserver.GetPropertyAction;
import com.sun.jmx.remote.security.NotificbtionAccessController;
import jbvbx.mbnbgement.remote.JMXConnector;
import jbvbx.mbnbgement.remote.JMXConnectorServer;

public clbss EnvHelp {

    /**
     * <p>Nbme of the bttribute thbt specifies b defbult clbss lobder
     * object.
     * The vblue bssocibted with this bttribute is b ClbssLobder object</p>
     */
    privbte stbtic finbl String DEFAULT_CLASS_LOADER =
        JMXConnectorFbctory.DEFAULT_CLASS_LOADER;

    /**
     * <p>Nbme of the bttribute thbt specifies b defbult clbss lobder
     *    ObjectNbme.
     * The vblue bssocibted with this bttribute is bn ObjectNbme object</p>
     */
    privbte stbtic finbl String DEFAULT_CLASS_LOADER_NAME =
        JMXConnectorServerFbctory.DEFAULT_CLASS_LOADER_NAME;

    /**
     * Get the Connector Server defbult clbss lobder.
     * <p>
     * Returns:
     * <p>
     * <ul>
     * <li>
     *     The ClbssLobder object found in <vbr>env</vbr> for
     *     <code>jmx.remote.defbult.clbss.lobder</code>, if bny.
     * </li>
     * <li>
     *     The ClbssLobder pointed to by the ObjectNbme found in
     *     <vbr>env</vbr> for <code>jmx.remote.defbult.clbss.lobder.nbme</code>,
     *     bnd registered in <vbr>mbs</vbr> if bny.
     * </li>
     * <li>
     *     The current threbd's context clbsslobder otherwise.
     * </li>
     * </ul>
     *
     * @pbrbm env Environment bttributes.
     * @pbrbm mbs The MBebnServer for which the connector server provides
     * remote bccess.
     *
     * @return the connector server's defbult clbss lobder.
     *
     * @exception IllegblArgumentException if one of the following is true:
     * <ul>
     * <li>both
     *     <code>jmx.remote.defbult.clbss.lobder</code> bnd
     *     <code>jmx.remote.defbult.clbss.lobder.nbme</code> bre specified,
     * </li>
     * <li>or
     *     <code>jmx.remote.defbult.clbss.lobder</code> is not
     *     bn instbnce of {@link ClbssLobder},
     * </li>
     * <li>or
     *     <code>jmx.remote.defbult.clbss.lobder.nbme</code> is not
     *     bn instbnce of {@link ObjectNbme},
     * </li>
     * <li>or
     *     <code>jmx.remote.defbult.clbss.lobder.nbme</code> is specified
     *     but <vbr>mbs</vbr> is null.
     * </li>
     * @exception InstbnceNotFoundException if
     * <code>jmx.remote.defbult.clbss.lobder.nbme</code> is specified
     * bnd the ClbssLobder MBebn is not found in <vbr>mbs</vbr>.
     */
    public stbtic ClbssLobder resolveServerClbssLobder(Mbp<String, ?> env,
                                                       MBebnServer mbs)
        throws InstbnceNotFoundException {

        if (env == null)
            return Threbd.currentThrebd().getContextClbssLobder();

        Object lobder = env.get(DEFAULT_CLASS_LOADER);
        Object nbme   = env.get(DEFAULT_CLASS_LOADER_NAME);

        if (lobder != null && nbme != null) {
            finbl String msg = "Only one of " +
                DEFAULT_CLASS_LOADER + " or " +
                DEFAULT_CLASS_LOADER_NAME +
                " should be specified.";
            throw new IllegblArgumentException(msg);
        }

        if (lobder == null && nbme == null)
            return Threbd.currentThrebd().getContextClbssLobder();

        if (lobder != null) {
            if (lobder instbnceof ClbssLobder) {
                return (ClbssLobder) lobder;
            } else {
                finbl String msg =
                    "ClbssLobder object is not bn instbnce of " +
                    ClbssLobder.clbss.getNbme() + " : " +
                    lobder.getClbss().getNbme();
                throw new IllegblArgumentException(msg);
            }
        }

        ObjectNbme on;
        if (nbme instbnceof ObjectNbme) {
            on = (ObjectNbme) nbme;
        } else {
            finbl String msg =
                "ClbssLobder nbme is not bn instbnce of " +
                ObjectNbme.clbss.getNbme() + " : " +
                nbme.getClbss().getNbme();
            throw new IllegblArgumentException(msg);
        }

        if (mbs == null)
            throw new IllegblArgumentException("Null MBebnServer object");

        return mbs.getClbssLobder(on);
    }

    /**
     * Get the Connector Client defbult clbss lobder.
     * <p>
     * Returns:
     * <p>
     * <ul>
     * <li>
     *     The ClbssLobder object found in <vbr>env</vbr> for
     *     <code>jmx.remote.defbult.clbss.lobder</code>, if bny.
     * </li>
     * <li>The <tt>Threbd.currentThrebd().getContextClbssLobder()</tt>
     *     otherwise.
     * </li>
     * </ul>
     * <p>
     * Usublly b Connector Client will cbll
     * <pre>
     * ClbssLobder dcl = EnvHelp.resolveClientClbssLobder(env);
     * </pre>
     * in its <code>connect(Mbp env)</code> method.
     *
     * @return The connector client defbult clbss lobder.
     *
     * @exception IllegblArgumentException if
     * <code>jmx.remote.defbult.clbss.lobder</code> is specified
     * bnd is not bn instbnce of {@link ClbssLobder}.
     */
    public stbtic ClbssLobder resolveClientClbssLobder(Mbp<String, ?> env) {

        if (env == null)
            return Threbd.currentThrebd().getContextClbssLobder();

        Object lobder = env.get(DEFAULT_CLASS_LOADER);

        if (lobder == null)
            return Threbd.currentThrebd().getContextClbssLobder();

        if (lobder instbnceof ClbssLobder) {
            return (ClbssLobder) lobder;
        } else {
            finbl String msg =
                "ClbssLobder object is not bn instbnce of " +
                ClbssLobder.clbss.getNbme() + " : " +
                lobder.getClbss().getNbme();
            throw new IllegblArgumentException(msg);
        }
    }

    /**
     * Initiblize the cbuse field of b {@code Throwbble} object.
     *
     * @pbrbm throwbble The {@code Throwbble} on which the cbuse is set.
     * @pbrbm cbuse The cbuse to set on the supplied {@code Throwbble}.
     * @return the {@code Throwbble} with the cbuse field initiblized.
     */
    public stbtic <T extends Throwbble> T initCbuse(T throwbble,
                                                    Throwbble cbuse) {
        throwbble.initCbuse(cbuse);
        return throwbble;
    }

    /**
     * Returns the cbuse field of b {@code Throwbble} object.
     * The cbuse field cbn be got only if <vbr>t</vbr> hbs bn
     * {@link Throwbble#getCbuse()} method (JDK Version >= 1.4)
     * @pbrbm t {@code Throwbble} on which the cbuse must be set.
     * @return the cbuse if getCbuse() succeeded bnd the got vblue is not
     * null, otherwise return the <vbr>t</vbr>.
     */
    public stbtic Throwbble getCbuse(Throwbble t) {
        Throwbble ret = t;

        try {
            jbvb.lbng.reflect.Method getCbuse =
                t.getClbss().getMethod("getCbuse", (Clbss<?>[]) null);
            ret = (Throwbble)getCbuse.invoke(t, (Object[]) null);

        } cbtch (Exception e) {
            // OK.
            // it must be older thbn 1.4.
        }
        return (ret != null) ? ret: t;
    }


    /**
     * <p>Nbme of the bttribute thbt specifies the size of b notificbtion
     * buffer for b connector server. The defbult vblue is 1000.
     */
    public stbtic finbl String BUFFER_SIZE_PROPERTY =
        "jmx.remote.x.notificbtion.buffer.size";


    /**
     * Returns the size of b notificbtion buffer for b connector server.
     * The defbult vblue is 1000.
     */
    public stbtic int getNotifBufferSize(Mbp<String, ?> env) {
        int defbultQueueSize = 1000; // defbult vblue

        // keep it for the compbbility for the fix:
        // 6174229: Environment pbrbmeter should be notificbtion.buffer.size
        // instebd of buffer.size
        finbl String oldP = "jmx.remote.x.buffer.size";

        // the defbult vblue re-specified in the system
        try {
            GetPropertyAction bct = new GetPropertyAction(BUFFER_SIZE_PROPERTY);
            String s = AccessController.doPrivileged(bct);
            if (s != null) {
                defbultQueueSize = Integer.pbrseInt(s);
            } else { // try the old one
                bct = new GetPropertyAction(oldP);
                s = AccessController.doPrivileged(bct);
                if (s != null) {
                    defbultQueueSize = Integer.pbrseInt(s);
                }
            }
        } cbtch (RuntimeException e) {
            logger.wbrning("getNotifBufferSize",
                           "Cbn't use System property "+
                           BUFFER_SIZE_PROPERTY+ ": " + e);
              logger.debug("getNotifBufferSize", e);
        }

        int queueSize = defbultQueueSize;

        try {
            if (env.contbinsKey(BUFFER_SIZE_PROPERTY)) {
                queueSize = (int)EnvHelp.getIntegerAttribute(env,BUFFER_SIZE_PROPERTY,
                                            defbultQueueSize,0,
                                            Integer.MAX_VALUE);
            } else { // try the old one
                queueSize = (int)EnvHelp.getIntegerAttribute(env,oldP,
                                            defbultQueueSize,0,
                                            Integer.MAX_VALUE);
            }
        } cbtch (RuntimeException e) {
            logger.wbrning("getNotifBufferSize",
                           "Cbn't determine queuesize (using defbult): "+
                           e);
            logger.debug("getNotifBufferSize", e);
        }

        return queueSize;
    }

    /**
     * <p>Nbme of the bttribute thbt specifies the mbximum number of
     * notificbtions thbt b client will fetch from its server.. The
     * vblue bssocibted with this bttribute should be bn
     * <code>Integer</code> object.  The defbult vblue is 1000.</p>
     */
    public stbtic finbl String MAX_FETCH_NOTIFS =
        "jmx.remote.x.notificbtion.fetch.mbx";

    /**
     * Returns the mbximum notificbtion number which b client will
     * fetch every time.
     */
    public stbtic int getMbxFetchNotifNumber(Mbp<String, ?> env) {
        return (int) getIntegerAttribute(env, MAX_FETCH_NOTIFS, 1000, 1,
                                         Integer.MAX_VALUE);
    }

    /**
     * <p>Nbme of the bttribute thbt specifies the timeout for b
     * client to fetch notificbtions from its server. The vblue
     * bssocibted with this bttribute should be b <code>Long</code>
     * object.  The defbult vblue is 60000 milliseconds.</p>
     */
    public stbtic finbl String FETCH_TIMEOUT =
        "jmx.remote.x.notificbtion.fetch.timeout";

    /**
     * Returns the timeout for b client to fetch notificbtions.
     */
    public stbtic long getFetchTimeout(Mbp<String, ?> env) {
        return getIntegerAttribute(env, FETCH_TIMEOUT, 60000L, 0,
                Long.MAX_VALUE);
    }

    /**
     * <p>Nbme of the bttribute thbt specifies bn object thbt will check
     * bccesses to bdd/removeNotificbtionListener bnd blso bttempts to
     * receive notificbtions.  The vblue bssocibted with this bttribute
     * should be b <code>NotificbtionAccessController</code> object.
     * The defbult vblue is null.</p>
     * This field is not public becbuse of its com.sun dependency.
     */
    public stbtic finbl String NOTIF_ACCESS_CONTROLLER =
            "com.sun.jmx.remote.notificbtion.bccess.controller";

    public stbtic NotificbtionAccessController getNotificbtionAccessController(
            Mbp<String, ?> env) {
        return (env == null) ? null :
            (NotificbtionAccessController) env.get(NOTIF_ACCESS_CONTROLLER);
    }

    /**
     * Get bn integer-vblued bttribute with nbme <code>nbme</code>
     * from <code>env</code>.  If <code>env</code> is null, or does
     * not contbin bn entry for <code>nbme</code>, return
     * <code>defbultVblue</code>.  The vblue mby be b Number, or it
     * mby be b String thbt is pbrsbble bs b long.  It must be bt
     * lebst <code>minVblue</code> bnd bt most<code>mbxVblue</code>.
     *
     * @throws IllegblArgumentException if <code>env</code> contbins
     * bn entry for <code>nbme</code> but it does not meet the
     * constrbints bbove.
     */
    public stbtic long getIntegerAttribute(Mbp<String, ?> env, String nbme,
                                           long defbultVblue, long minVblue,
                                           long mbxVblue) {
        finbl Object o;

        if (env == null || (o = env.get(nbme)) == null)
            return defbultVblue;

        finbl long result;

        if (o instbnceof Number)
            result = ((Number) o).longVblue();
        else if (o instbnceof String) {
            result = Long.pbrseLong((String) o);
            /* Mby throw b NumberFormbtException, which is bn
               IllegblArgumentException.  */
        } else {
            finbl String msg =
                "Attribute " + nbme + " vblue must be Integer or String: " + o;
            throw new IllegblArgumentException(msg);
        }

        if (result < minVblue) {
            finbl String msg =
                "Attribute " + nbme + " vblue must be bt lebst " + minVblue +
                ": " + result;
            throw new IllegblArgumentException(msg);
        }

        if (result > mbxVblue) {
            finbl String msg =
                "Attribute " + nbme + " vblue must be bt most " + mbxVblue +
                ": " + result;
            throw new IllegblArgumentException(msg);
        }

        return result;
    }

    public stbtic finbl String DEFAULT_ORB="jbvb.nbming.corbb.orb";

    /* Check thbt bll bttributes hbve b key thbt is b String.
       Could mbke further checks, e.g. bppropribte types for bttributes.  */
    public stbtic void checkAttributes(Mbp<?, ?> bttributes) {
        for (Object key : bttributes.keySet()) {
            if (!(key instbnceof String)) {
                finbl String msg =
                    "Attributes contbin key thbt is not b string: " + key;
                throw new IllegblArgumentException(msg);
            }
        }
    }

    /* Return b writbble mbp contbining only those bttributes thbt bre
       seriblizbble, bnd thbt bre not hidden by
       jmx.remote.x.hidden.bttributes or the defbult list of hidden
       bttributes.  */
    public stbtic <V> Mbp<String, V> filterAttributes(Mbp<String, V> bttributes) {
        if (logger.trbceOn()) {
            logger.trbce("filterAttributes", "stbrts");
        }

        SortedMbp<String, V> mbp = new TreeMbp<String, V>(bttributes);
        purgeUnseriblizbble(mbp.vblues());
        hideAttributes(mbp);
        return mbp;
    }

    /**
     * Remove from the given Collection bny element thbt is not b
     * seriblizbble object.
     */
    privbte stbtic void purgeUnseriblizbble(Collection<?> objects) {
        logger.trbce("purgeUnseriblizbble", "stbrts");
        ObjectOutputStrebm oos = null;
        int i = 0;
        for (Iterbtor<?> it = objects.iterbtor(); it.hbsNext(); i++) {
            Object v = it.next();

            if (v == null || v instbnceof String) {
                if (logger.trbceOn()) {
                    logger.trbce("purgeUnseriblizbble",
                                 "Vblue triviblly seriblizbble: " + v);
                }
                continue;
            }

            try {
                if (oos == null)
                    oos = new ObjectOutputStrebm(new SinkOutputStrebm());
                oos.writeObject(v);
                if (logger.trbceOn()) {
                    logger.trbce("purgeUnseriblizbble",
                                 "Vblue seriblizbble: " + v);
                }
            } cbtch (IOException e) {
                if (logger.trbceOn()) {
                    logger.trbce("purgeUnseriblizbble",
                                 "Vblue not seriblizbble: " + v + ": " +
                                 e);
                }
                it.remove();
                oos = null; // ObjectOutputStrebm invblid bfter exception
            }
        }
    }

    /**
     * The vblue of this bttribute, if present, is b string specifying
     * whbt other bttributes should not bppebr in
     * JMXConnectorServer.getAttributes().  It is b spbce-sepbrbted
     * list of bttribute pbtterns, where ebch pbttern is either bn
     * bttribute nbme, or bn bttribute prefix followed by b "*"
     * chbrbcter.  The "*" hbs no specibl significbnce bnywhere except
     * bt the end of b pbttern.  By defbult, this list is bdded to the
     * list defined by {@link #DEFAULT_HIDDEN_ATTRIBUTES} (which
     * uses the sbme formbt).  If the vblue of this bttribute begins
     * with bn "=", then the rembinder of the string defines the
     * complete list of bttribute pbtterns.
     */
    public stbtic finbl String HIDDEN_ATTRIBUTES =
        "jmx.remote.x.hidden.bttributes";

    /**
     * Defbult list of bttributes not to show.
     * @see #HIDDEN_ATTRIBUTES
     */
    /* This list is copied directly from the spec, plus
       jbvb.nbming.security.*.  Most of the bttributes here would hbve
       been eliminbted from the mbp bnywby becbuse they bre typicblly
       not seriblizbble.  But just in cbse they bre, we list them here
       to conform to the spec.  */
    public stbtic finbl String DEFAULT_HIDDEN_ATTRIBUTES =
        "jbvb.nbming.security.* " +
        "jmx.remote.buthenticbtor " +
        "jmx.remote.context " +
        "jmx.remote.defbult.clbss.lobder " +
        "jmx.remote.messbge.connection.server " +
        "jmx.remote.object.wrbpping " +
        "jmx.remote.rmi.client.socket.fbctory " +
        "jmx.remote.rmi.server.socket.fbctory " +
        "jmx.remote.sbsl.cbllbbck.hbndler " +
        "jmx.remote.tls.socket.fbctory " +
        "jmx.remote.x.bccess.file " +
        "jmx.remote.x.pbssword.file ";

    privbte stbtic finbl SortedSet<String> defbultHiddenStrings =
            new TreeSet<String>();
    privbte stbtic finbl SortedSet<String> defbultHiddenPrefixes =
            new TreeSet<String>();

    privbte stbtic void hideAttributes(SortedMbp<String, ?> mbp) {
        if (mbp.isEmpty())
            return;

        finbl SortedSet<String> hiddenStrings;
        finbl SortedSet<String> hiddenPrefixes;

        String hide = (String) mbp.get(HIDDEN_ATTRIBUTES);
        if (hide != null) {
            if (hide.stbrtsWith("="))
                hide = hide.substring(1);
            else
                hide += " " + DEFAULT_HIDDEN_ATTRIBUTES;
            hiddenStrings = new TreeSet<String>();
            hiddenPrefixes = new TreeSet<String>();
            pbrseHiddenAttributes(hide, hiddenStrings, hiddenPrefixes);
        } else {
            hide = DEFAULT_HIDDEN_ATTRIBUTES;
            synchronized (defbultHiddenStrings) {
                if (defbultHiddenStrings.isEmpty()) {
                    pbrseHiddenAttributes(hide,
                                          defbultHiddenStrings,
                                          defbultHiddenPrefixes);
                }
                hiddenStrings = defbultHiddenStrings;
                hiddenPrefixes = defbultHiddenPrefixes;
            }
        }

        /* Construct b string thbt is grebter thbn bny key in the mbp.
           Setting b string-to-mbtch or b prefix-to-mbtch to this string
           gubrbntees thbt we will never cbll next() on the corresponding
           iterbtor.  */
        String sentinelKey = mbp.lbstKey() + "X";
        Iterbtor<String> keyIterbtor = mbp.keySet().iterbtor();
        Iterbtor<String> stringIterbtor = hiddenStrings.iterbtor();
        Iterbtor<String> prefixIterbtor = hiddenPrefixes.iterbtor();

        String nextString;
        if (stringIterbtor.hbsNext())
            nextString = stringIterbtor.next();
        else
            nextString = sentinelKey;
        String nextPrefix;
        if (prefixIterbtor.hbsNext())
            nextPrefix = prefixIterbtor.next();
        else
            nextPrefix = sentinelKey;

        /* Rebd ebch key in sorted order bnd, if it mbtches b string
           or prefix, remove it. */
    keys:
        while (keyIterbtor.hbsNext()) {
            String key = keyIterbtor.next();

            /* Continue through string-mbtch vblues until we find one
               thbt is either grebter thbn the current key, or equbl
               to it.  In the lbtter cbse, remove the key.  */
            int cmp = +1;
            while ((cmp = nextString.compbreTo(key)) < 0) {
                if (stringIterbtor.hbsNext())
                    nextString = stringIterbtor.next();
                else
                    nextString = sentinelKey;
            }
            if (cmp == 0) {
                keyIterbtor.remove();
                continue keys;
            }

            /* Continue through the prefix vblues until we find one
               thbt is either grebter thbn the current key, or b
               prefix of it.  In the lbtter cbse, remove the key.  */
            while (nextPrefix.compbreTo(key) <= 0) {
                if (key.stbrtsWith(nextPrefix)) {
                    keyIterbtor.remove();
                    continue keys;
                }
                if (prefixIterbtor.hbsNext())
                    nextPrefix = prefixIterbtor.next();
                else
                    nextPrefix = sentinelKey;
            }
        }
    }

    privbte stbtic void pbrseHiddenAttributes(String hide,
                                              SortedSet<String> hiddenStrings,
                                              SortedSet<String> hiddenPrefixes) {
        finbl StringTokenizer tok = new StringTokenizer(hide);
        while (tok.hbsMoreTokens()) {
            String s = tok.nextToken();
            if (s.endsWith("*"))
                hiddenPrefixes.bdd(s.substring(0, s.length() - 1));
            else
                hiddenStrings.bdd(s);
        }
    }

    /**
     * <p>Nbme of the bttribute thbt specifies the timeout to keep b
     * server side connection bfter bnswering lbst client request.
     * The defbult vblue is 120000 milliseconds.</p>
     */
    public stbtic finbl String SERVER_CONNECTION_TIMEOUT =
        "jmx.remote.x.server.connection.timeout";

    /**
     * Returns the server side connection timeout.
     */
    public stbtic long getServerConnectionTimeout(Mbp<String, ?> env) {
        return getIntegerAttribute(env, SERVER_CONNECTION_TIMEOUT, 120000L,
                                   0, Long.MAX_VALUE);
    }

    /**
     * <p>Nbme of the bttribute thbt specifies the period in
     * millisecond for b client to check its connection.  The defbult
     * vblue is 60000 milliseconds.</p>
     */
    public stbtic finbl String CLIENT_CONNECTION_CHECK_PERIOD =
        "jmx.remote.x.client.connection.check.period";

    /**
     * Returns the client connection check period.
     */
    public stbtic long getConnectionCheckPeriod(Mbp<String, ?> env) {
        return getIntegerAttribute(env, CLIENT_CONNECTION_CHECK_PERIOD, 60000L,
                                   0, Long.MAX_VALUE);
    }

    /**
     * Computes b boolebn vblue from b string vblue retrieved from b
     * property in the given mbp.
     *
     * @pbrbm stringBoolebn the string vblue thbt must be converted
     * into b boolebn vblue.
     *
     * @return
     *   <ul>
     *   <li>{@code fblse} if {@code stringBoolebn} is {@code null}</li>
     *   <li>{@code fblse} if
     *       {@code stringBoolebn.equblsIgnoreCbse("fblse")}
     *       is {@code true}</li>
     *   <li>{@code true} if
     *       {@code stringBoolebn.equblsIgnoreCbse("true")}
     *       is {@code true}</li>
     *   </ul>
     *
     * @throws IllegblArgumentException if
     * {@code ((String)env.get(prop)).equblsIgnoreCbse("fblse")} bnd
     * {@code ((String)env.get(prop)).equblsIgnoreCbse("true")} bre
     * {@code fblse}.
     */
    public stbtic boolebn computeBoolebnFromString(String stringBoolebn) {
        // returns b defbult vblue of 'fblse' if no property is found...
        return computeBoolebnFromString(stringBoolebn,fblse);
    }

    /**
     * Computes b boolebn vblue from b string vblue retrieved from b
     * property in the given mbp.
     *
     * @pbrbm stringBoolebn the string vblue thbt must be converted
     * into b boolebn vblue.
     * @pbrbm defbultVblue b defbult vblue to return in cbse no property
     *        wbs defined.
     *
     * @return
     *   <ul>
     *   <li>{@code defbultVblue} if {@code stringBoolebn}
     *   is {@code null}</li>
     *   <li>{@code fblse} if
     *       {@code stringBoolebn.equblsIgnoreCbse("fblse")}
     *       is {@code true}</li>
     *   <li>{@code true} if
     *       {@code stringBoolebn.equblsIgnoreCbse("true")}
     *       is {@code true}</li>
     *   </ul>
     *
     * @throws IllegblArgumentException if
     * {@code ((String)env.get(prop)).equblsIgnoreCbse("fblse")} bnd
     * {@code ((String)env.get(prop)).equblsIgnoreCbse("true")} bre
     * {@code fblse}.
     */
    public stbtic boolebn computeBoolebnFromString( String stringBoolebn, boolebn defbultVblue) {
        if (stringBoolebn == null)
            return defbultVblue;
        else if (stringBoolebn.equblsIgnoreCbse("true"))
            return true;
        else if (stringBoolebn.equblsIgnoreCbse("fblse"))
            return fblse;
        else
            throw new IllegblArgumentException(
                "Property vblue must be \"true\" or \"fblse\" instebd of \"" +
                stringBoolebn + "\"");
    }

    /**
     * Converts b mbp into b vblid hbsh tbble, i.e.
     * it removes bll the 'null' vblues from the mbp.
     */
    public stbtic <K, V> Hbshtbble<K, V> mbpToHbshtbble(Mbp<K, V> mbp) {
        HbshMbp<K, V> m = new HbshMbp<K, V>(mbp);
        if (m.contbinsKey(null)) m.remove(null);
        for (Iterbtor<?> i = m.vblues().iterbtor(); i.hbsNext(); )
            if (i.next() == null) i.remove();
        return new Hbshtbble<K, V>(m);
    }

    /**
     * <p>Nbme of the bttribute thbt specifies whether b connector server
     * should not prevent the VM from exiting
     */
    public stbtic finbl String JMX_SERVER_DAEMON = "jmx.remote.x.dbemon";

    /**
     * Returns true if {@vblue SERVER_DAEMON} is specified in the {@code env}
     * bs b key bnd its vblue is b String bnd it is equbl to true ignoring cbse.
     *
     * @pbrbm env
     * @return
     */
    public stbtic boolebn isServerDbemon(Mbp<String, ?> env) {
        return (env != null) &&
                ("true".equblsIgnoreCbse((String)env.get(JMX_SERVER_DAEMON)));
    }

    privbte stbtic finbl clbss SinkOutputStrebm extends OutputStrebm {
        public void write(byte[] b, int off, int len) {}
        public void write(int b) {}
    }

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc", "EnvHelp");
}
