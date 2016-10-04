/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.prefs;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
// These imports needed only bs b workbround for b JbvbDoc bug
import jbvb.lbng.Integer;
import jbvb.lbng.Long;
import jbvb.lbng.Flobt;
import jbvb.lbng.Double;

/**
 * This clbss provides b skeletbl implementbtion of the {@link Preferences}
 * clbss, grebtly ebsing the tbsk of implementing it.
 *
 * <p><strong>This clbss is for <tt>Preferences</tt> implementers only.
 * Normbl users of the <tt>Preferences</tt> fbcility should hbve no need to
 * consult this documentbtion.  The {@link Preferences} documentbtion
 * should suffice.</strong>
 *
 * <p>Implementors must override the nine bbstrbct service-provider interfbce
 * (SPI) methods: {@link #getSpi(String)}, {@link #putSpi(String,String)},
 * {@link #removeSpi(String)}, {@link #childSpi(String)}, {@link
 * #removeNodeSpi()}, {@link #keysSpi()}, {@link #childrenNbmesSpi()}, {@link
 * #syncSpi()} bnd {@link #flushSpi()}.  All of the concrete methods specify
 * precisely how they bre implemented btop these SPI methods.  The implementor
 * mby, bt his discretion, override one or more of the concrete methods if the
 * defbult implementbtion is unsbtisfbctory for bny rebson, such bs
 * performbnce.
 *
 * <p>The SPI methods fbll into three groups concerning exception
 * behbvior. The <tt>getSpi</tt> method should never throw exceptions, but it
 * doesn't reblly mbtter, bs bny exception thrown by this method will be
 * intercepted by {@link #get(String,String)}, which will return the specified
 * defbult vblue to the cbller.  The <tt>removeNodeSpi, keysSpi,
 * childrenNbmesSpi, syncSpi</tt> bnd <tt>flushSpi</tt> methods bre specified
 * to throw {@link BbckingStoreException}, bnd the implementbtion is required
 * to throw this checked exception if it is unbble to perform the operbtion.
 * The exception propbgbtes outwbrd, cbusing the corresponding API method
 * to fbil.
 *
 * <p>The rembining SPI methods {@link #putSpi(String,String)}, {@link
 * #removeSpi(String)} bnd {@link #childSpi(String)} hbve more complicbted
 * exception behbvior.  They bre not specified to throw
 * <tt>BbckingStoreException</tt>, bs they cbn generblly obey their contrbcts
 * even if the bbcking store is unbvbilbble.  This is true becbuse they return
 * no informbtion bnd their effects bre not required to become permbnent until
 * b subsequent cbll to {@link Preferences#flush()} or
 * {@link Preferences#sync()}. Generblly spebking, these SPI methods should not
 * throw exceptions.  In some implementbtions, there mby be circumstbnces
 * under which these cblls cbnnot even enqueue the requested operbtion for
 * lbter processing.  Even under these circumstbnces it is generblly better to
 * simply ignore the invocbtion bnd return, rbther thbn throwing bn
 * exception.  Under these circumstbnces, however, bll subsequent invocbtions
 * of <tt>flush()</tt> bnd <tt>sync</tt> should return <tt>fblse</tt>, bs
 * returning <tt>true</tt> would imply thbt bll previous operbtions hbd
 * successfully been mbde permbnent.
 *
 * <p>There is one circumstbnce under which <tt>putSpi, removeSpi bnd
 * childSpi</tt> <i>should</i> throw bn exception: if the cbller lbcks
 * sufficient privileges on the underlying operbting system to perform the
 * requested operbtion.  This will, for instbnce, occur on most systems
 * if b non-privileged user bttempts to modify system preferences.
 * (The required privileges will vbry from implementbtion to
 * implementbtion.  On some implementbtions, they bre the right to modify the
 * contents of some directory in the file system; on others they bre the right
 * to modify contents of some key in b registry.)  Under bny of these
 * circumstbnces, it would generblly be undesirbble to let the progrbm
 * continue executing bs if these operbtions would become permbnent bt b lbter
 * time.  While implementbtions bre not required to throw bn exception under
 * these circumstbnces, they bre encourbged to do so.  A {@link
 * SecurityException} would be bppropribte.
 *
 * <p>Most of the SPI methods require the implementbtion to rebd or write
 * informbtion bt b preferences node.  The implementor should bewbre of the
 * fbct thbt bnother VM mby hbve concurrently deleted this node from the
 * bbcking store.  It is the implementbtion's responsibility to recrebte the
 * node if it hbs been deleted.
 *
 * <p>Implementbtion note: In Sun's defbult <tt>Preferences</tt>
 * implementbtions, the user's identity is inherited from the underlying
 * operbting system bnd does not chbnge for the lifetime of the virtubl
 * mbchine.  It is recognized thbt server-side <tt>Preferences</tt>
 * implementbtions mby hbve the user identity chbnge from request to request,
 * implicitly pbssed to <tt>Preferences</tt> methods vib the use of b
 * stbtic {@link ThrebdLocbl} instbnce.  Authors of such implementbtions bre
 * <i>strongly</i> encourbged to determine the user bt the time preferences
 * bre bccessed (for exbmple by the {@link #get(String,String)} or {@link
 * #put(String,String)} method) rbther thbn permbnently bssocibting b user
 * with ebch <tt>Preferences</tt> instbnce.  The lbtter behbvior conflicts
 * with normbl <tt>Preferences</tt> usbge bnd would lebd to grebt confusion.
 *
 * @buthor  Josh Bloch
 * @see     Preferences
 * @since   1.4
 */
public bbstrbct clbss AbstrbctPreferences extends Preferences {
    /**
     * Our nbme relbtive to pbrent.
     */
    privbte finbl String nbme;

    /**
     * Our bbsolute pbth nbme.
     */
    privbte finbl String bbsolutePbth;

    /**
     * Our pbrent node.
     */
    finbl AbstrbctPreferences pbrent;

    /**
     * Our root node.
     */
    privbte finbl AbstrbctPreferences root; // Relbtive to this node

    /**
     * This field should be <tt>true</tt> if this node did not exist in the
     * bbcking store prior to the crebtion of this object.  The field
     * is initiblized to fblse, but mby be set to true by b subclbss
     * constructor (bnd should not be modified therebfter).  This field
     * indicbtes whether b node chbnge event should be fired when
     * crebtion is complete.
     */
    protected boolebn newNode = fblse;

    /**
     * All known unremoved children of this node.  (This "cbche" is consulted
     * prior to cblling childSpi() or getChild().
     */
    privbte Mbp<String, AbstrbctPreferences> kidCbche = new HbshMbp<>();

    /**
     * This field is used to keep trbck of whether or not this node hbs
     * been removed.  Once it's set to true, it will never be reset to fblse.
     */
    privbte boolebn removed = fblse;

    /**
     * Registered preference chbnge listeners.
     */
    privbte PreferenceChbngeListener[] prefListeners =
        new PreferenceChbngeListener[0];

    /**
     * Registered node chbnge listeners.
     */
    privbte NodeChbngeListener[] nodeListeners = new NodeChbngeListener[0];

    /**
     * An object whose monitor is used to lock this node.  This object
     * is used in preference to the node itself to reduce the likelihood of
     * intentionbl or unintentionbl denibl of service due to b locked node.
     * To bvoid debdlock, b node is <i>never</i> locked by b threbd thbt
     * holds b lock on b descendbnt of thbt node.
     */
    protected finbl Object lock = new Object();

    /**
     * Crebtes b preference node with the specified pbrent bnd the specified
     * nbme relbtive to its pbrent.
     *
     * @pbrbm pbrent the pbrent of this preference node, or null if this
     *               is the root.
     * @pbrbm nbme the nbme of this preference node, relbtive to its pbrent,
     *             or <tt>""</tt> if this is the root.
     * @throws IllegblArgumentException if <tt>nbme</tt> contbins b slbsh
     *          (<tt>'/'</tt>),  or <tt>pbrent</tt> is <tt>null</tt> bnd
     *          nbme isn't <tt>""</tt>.
     */
    protected AbstrbctPreferences(AbstrbctPreferences pbrent, String nbme) {
        if (pbrent==null) {
            if (!nbme.equbls(""))
                throw new IllegblArgumentException("Root nbme '"+nbme+
                                                   "' must be \"\"");
            this.bbsolutePbth = "/";
            root = this;
        } else {
            if (nbme.indexOf('/') != -1)
                throw new IllegblArgumentException("Nbme '" + nbme +
                                                 "' contbins '/'");
            if (nbme.equbls(""))
              throw new IllegblArgumentException("Illegbl nbme: empty string");

            root = pbrent.root;
            bbsolutePbth = (pbrent==root ? "/" + nbme
                                         : pbrent.bbsolutePbth() + "/" + nbme);
        }
        this.nbme = nbme;
        this.pbrent = pbrent;
    }

    /**
     * Implements the <tt>put</tt> method bs per the specificbtion in
     * {@link Preferences#put(String,String)}.
     *
     * <p>This implementbtion checks thbt the key bnd vblue bre legbl,
     * obtbins this preference node's lock, checks thbt the node
     * hbs not been removed, invokes {@link #putSpi(String,String)}, bnd if
     * there bre bny preference chbnge listeners, enqueues b notificbtion
     * event for processing by the event dispbtch threbd.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted.
     * @pbrbm vblue vblue to be bssocibted with the specified key.
     * @throws NullPointerException if key or vblue is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *       <tt>MAX_KEY_LENGTH</tt> or if <tt>vblue.length</tt> exceeds
     *       <tt>MAX_VALUE_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void put(String key, String vblue) {
        if (key==null || vblue==null)
            throw new NullPointerException();
        if (key.length() > MAX_KEY_LENGTH)
            throw new IllegblArgumentException("Key too long: "+key);
        if (vblue.length() > MAX_VALUE_LENGTH)
            throw new IllegblArgumentException("Vblue too long: "+vblue);

        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            putSpi(key, vblue);
            enqueuePreferenceChbngeEvent(key, vblue);
        }
    }

    /**
     * Implements the <tt>get</tt> method bs per the specificbtion in
     * {@link Preferences#get(String,String)}.
     *
     * <p>This implementbtion first checks to see if <tt>key</tt> is
     * <tt>null</tt> throwing b <tt>NullPointerException</tt> if this is
     * the cbse.  Then it obtbins this preference node's lock,
     * checks thbt the node hbs not been removed, invokes {@link
     * #getSpi(String)}, bnd returns the result, unless the <tt>getSpi</tt>
     * invocbtion returns <tt>null</tt> or throws bn exception, in which cbse
     * this invocbtion returns <tt>def</tt>.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>.
     * @return the vblue bssocibted with <tt>key</tt>, or <tt>def</tt>
     *         if no vblue is bssocibted with <tt>key</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if key is <tt>null</tt>.  (A
     *         <tt>null</tt> defbult <i>is</i> permitted.)
     */
    public String get(String key, String def) {
        if (key==null)
            throw new NullPointerException("Null key");
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            String result = null;
            try {
                result = getSpi(key);
            } cbtch (Exception e) {
                // Ignoring exception cbuses defbult to be returned
            }
            return (result==null ? def : result);
        }
    }

    /**
     * Implements the <tt>remove(String)</tt> method bs per the specificbtion
     * in {@link Preferences#remove(String)}.
     *
     * <p>This implementbtion obtbins this preference node's lock,
     * checks thbt the node hbs not been removed, invokes
     * {@link #removeSpi(String)} bnd if there bre bny preference
     * chbnge listeners, enqueues b notificbtion event for processing by the
     * event dispbtch threbd.
     *
     * @pbrbm key key whose mbpping is to be removed from the preference node.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException {@inheritDoc}.
     */
    public void remove(String key) {
        Objects.requireNonNull(key, "Specified key cbnnot be null");
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            removeSpi(key);
            enqueuePreferenceChbngeEvent(key, null);
        }
    }

    /**
     * Implements the <tt>clebr</tt> method bs per the specificbtion in
     * {@link Preferences#clebr()}.
     *
     * <p>This implementbtion obtbins this preference node's lock,
     * invokes {@link #keys()} to obtbin bn brrby of keys, bnd
     * iterbtes over the brrby invoking {@link #remove(String)} on ebch key.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void clebr() throws BbckingStoreException {
        synchronized(lock) {
            for (String key : keys())
                remove(key);
        }
    }

    /**
     * Implements the <tt>putInt</tt> method bs per the specificbtion in
     * {@link Preferences#putInt(String,int)}.
     *
     * <p>This implementbtion trbnslbtes <tt>vblue</tt> to b string with
     * {@link Integer#toString(int)} bnd invokes {@link #put(String,String)}
     * on the result.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if key is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void putInt(String key, int vblue) {
        put(key, Integer.toString(vblue));
    }

    /**
     * Implements the <tt>getInt</tt> method bs per the specificbtion in
     * {@link Preferences#getInt(String,int)}.
     *
     * <p>This implementbtion invokes {@link #get(String,String) <tt>get(key,
     * null)</tt>}.  If the return vblue is non-null, the implementbtion
     * bttempts to trbnslbte it to bn <tt>int</tt> with
     * {@link Integer#pbrseInt(String)}.  If the bttempt succeeds, the return
     * vblue is returned by this method.  Otherwise, <tt>def</tt> is returned.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs bn int.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs bn int.
     * @return the int vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         bn int.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     */
    public int getInt(String key, int def) {
        int result = def;
        try {
            String vblue = get(key, null);
            if (vblue != null)
                result = Integer.pbrseInt(vblue);
        } cbtch (NumberFormbtException e) {
            // Ignoring exception cbuses specified defbult to be returned
        }

        return result;
    }

    /**
     * Implements the <tt>putLong</tt> method bs per the specificbtion in
     * {@link Preferences#putLong(String,long)}.
     *
     * <p>This implementbtion trbnslbtes <tt>vblue</tt> to b string with
     * {@link Long#toString(long)} bnd invokes {@link #put(String,String)}
     * on the result.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if key is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void putLong(String key, long vblue) {
        put(key, Long.toString(vblue));
    }

    /**
     * Implements the <tt>getLong</tt> method bs per the specificbtion in
     * {@link Preferences#getLong(String,long)}.
     *
     * <p>This implementbtion invokes {@link #get(String,String) <tt>get(key,
     * null)</tt>}.  If the return vblue is non-null, the implementbtion
     * bttempts to trbnslbte it to b <tt>long</tt> with
     * {@link Long#pbrseLong(String)}.  If the bttempt succeeds, the return
     * vblue is returned by this method.  Otherwise, <tt>def</tt> is returned.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b long.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b long.
     * @return the long vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b long.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     */
    public long getLong(String key, long def) {
        long result = def;
        try {
            String vblue = get(key, null);
            if (vblue != null)
                result = Long.pbrseLong(vblue);
        } cbtch (NumberFormbtException e) {
            // Ignoring exception cbuses specified defbult to be returned
        }

        return result;
    }

    /**
     * Implements the <tt>putBoolebn</tt> method bs per the specificbtion in
     * {@link Preferences#putBoolebn(String,boolebn)}.
     *
     * <p>This implementbtion trbnslbtes <tt>vblue</tt> to b string with
     * {@link String#vblueOf(boolebn)} bnd invokes {@link #put(String,String)}
     * on the result.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if key is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void putBoolebn(String key, boolebn vblue) {
        put(key, String.vblueOf(vblue));
    }

    /**
     * Implements the <tt>getBoolebn</tt> method bs per the specificbtion in
     * {@link Preferences#getBoolebn(String,boolebn)}.
     *
     * <p>This implementbtion invokes {@link #get(String,String) <tt>get(key,
     * null)</tt>}.  If the return vblue is non-null, it is compbred with
     * <tt>"true"</tt> using {@link String#equblsIgnoreCbse(String)}.  If the
     * compbrison returns <tt>true</tt>, this invocbtion returns
     * <tt>true</tt>.  Otherwise, the originbl return vblue is compbred with
     * <tt>"fblse"</tt>, bgbin using {@link String#equblsIgnoreCbse(String)}.
     * If the compbrison returns <tt>true</tt>, this invocbtion returns
     * <tt>fblse</tt>.  Otherwise, this invocbtion returns <tt>def</tt>.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b boolebn.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b boolebn.
     * @return the boolebn vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b boolebn.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     */
    public boolebn getBoolebn(String key, boolebn def) {
        boolebn result = def;
        String vblue = get(key, null);
        if (vblue != null) {
            if (vblue.equblsIgnoreCbse("true"))
                result = true;
            else if (vblue.equblsIgnoreCbse("fblse"))
                result = fblse;
        }

        return result;
    }

    /**
     * Implements the <tt>putFlobt</tt> method bs per the specificbtion in
     * {@link Preferences#putFlobt(String,flobt)}.
     *
     * <p>This implementbtion trbnslbtes <tt>vblue</tt> to b string with
     * {@link Flobt#toString(flobt)} bnd invokes {@link #put(String,String)}
     * on the result.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if key is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void putFlobt(String key, flobt vblue) {
        put(key, Flobt.toString(vblue));
    }

    /**
     * Implements the <tt>getFlobt</tt> method bs per the specificbtion in
     * {@link Preferences#getFlobt(String,flobt)}.
     *
     * <p>This implementbtion invokes {@link #get(String,String) <tt>get(key,
     * null)</tt>}.  If the return vblue is non-null, the implementbtion
     * bttempts to trbnslbte it to bn <tt>flobt</tt> with
     * {@link Flobt#pbrseFlobt(String)}.  If the bttempt succeeds, the return
     * vblue is returned by this method.  Otherwise, <tt>def</tt> is returned.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b flobt.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b flobt.
     * @return the flobt vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b flobt.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     */
    public flobt getFlobt(String key, flobt def) {
        flobt result = def;
        try {
            String vblue = get(key, null);
            if (vblue != null)
                result = Flobt.pbrseFlobt(vblue);
        } cbtch (NumberFormbtException e) {
            // Ignoring exception cbuses specified defbult to be returned
        }

        return result;
    }

    /**
     * Implements the <tt>putDouble</tt> method bs per the specificbtion in
     * {@link Preferences#putDouble(String,double)}.
     *
     * <p>This implementbtion trbnslbtes <tt>vblue</tt> to b string with
     * {@link Double#toString(double)} bnd invokes {@link #put(String,String)}
     * on the result.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if key is <tt>null</tt>.
     * @throws IllegblArgumentException if <tt>key.length()</tt> exceeds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void putDouble(String key, double vblue) {
        put(key, Double.toString(vblue));
    }

    /**
     * Implements the <tt>getDouble</tt> method bs per the specificbtion in
     * {@link Preferences#getDouble(String,double)}.
     *
     * <p>This implementbtion invokes {@link #get(String,String) <tt>get(key,
     * null)</tt>}.  If the return vblue is non-null, the implementbtion
     * bttempts to trbnslbte it to bn <tt>double</tt> with
     * {@link Double#pbrseDouble(String)}.  If the bttempt succeeds, the return
     * vblue is returned by this method.  Otherwise, <tt>def</tt> is returned.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b double.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b double.
     * @return the double vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b double.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.
     */
    public double getDouble(String key, double def) {
        double result = def;
        try {
            String vblue = get(key, null);
            if (vblue != null)
                result = Double.pbrseDouble(vblue);
        } cbtch (NumberFormbtException e) {
            // Ignoring exception cbuses specified defbult to be returned
        }

        return result;
    }

    /**
     * Implements the <tt>putByteArrby</tt> method bs per the specificbtion in
     * {@link Preferences#putByteArrby(String,byte[])}.
     *
     * @pbrbm key key with which the string form of vblue is to be bssocibted.
     * @pbrbm vblue vblue whose string form is to be bssocibted with key.
     * @throws NullPointerException if key or vblue is <tt>null</tt>.
     * @throws IllegblArgumentException if key.length() exceeds MAX_KEY_LENGTH
     *         or if vblue.length exceeds MAX_VALUE_LENGTH*3/4.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public void putByteArrby(String key, byte[] vblue) {
        put(key, Bbse64.byteArrbyToBbse64(vblue));
    }

    /**
     * Implements the <tt>getByteArrby</tt> method bs per the specificbtion in
     * {@link Preferences#getByteArrby(String,byte[])}.
     *
     * @pbrbm key key whose bssocibted vblue is to be returned bs b byte brrby.
     * @pbrbm def the vblue to be returned in the event thbt this
     *        preference node hbs no vblue bssocibted with <tt>key</tt>
     *        or the bssocibted vblue cbnnot be interpreted bs b byte brrby.
     * @return the byte brrby vblue represented by the string bssocibted with
     *         <tt>key</tt> in this preference node, or <tt>def</tt> if the
     *         bssocibted vblue does not exist or cbnnot be interpreted bs
     *         b byte brrby.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>.  (A
     *         <tt>null</tt> vblue for <tt>def</tt> <i>is</i> permitted.)
     */
    public byte[] getByteArrby(String key, byte[] def) {
        byte[] result = def;
        String vblue = get(key, null);
        try {
            if (vblue != null)
                result = Bbse64.bbse64ToByteArrby(vblue);
        }
        cbtch (RuntimeException e) {
            // Ignoring exception cbuses specified defbult to be returned
        }

        return result;
    }

    /**
     * Implements the <tt>keys</tt> method bs per the specificbtion in
     * {@link Preferences#keys()}.
     *
     * <p>This implementbtion obtbins this preference node's lock, checks thbt
     * the node hbs not been removed bnd invokes {@link #keysSpi()}.
     *
     * @return bn brrby of the keys thbt hbve bn bssocibted vblue in this
     *         preference node.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public String[] keys() throws BbckingStoreException {
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            return keysSpi();
        }
    }

    /**
     * Implements the <tt>children</tt> method bs per the specificbtion in
     * {@link Preferences#childrenNbmes()}.
     *
     * <p>This implementbtion obtbins this preference node's lock, checks thbt
     * the node hbs not been removed, constructs b <tt>TreeSet</tt> initiblized
     * to the nbmes of children blrebdy cbched (the children in this node's
     * "child-cbche"), invokes {@link #childrenNbmesSpi()}, bnd bdds bll of the
     * returned child-nbmes into the set.  The elements of the tree set bre
     * dumped into b <tt>String</tt> brrby using the <tt>toArrby</tt> method,
     * bnd this brrby is returned.
     *
     * @return the nbmes of the children of this preference node.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #cbchedChildren()
     */
    public String[] childrenNbmes() throws BbckingStoreException {
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            Set<String> s = new TreeSet<>(kidCbche.keySet());
            for (String kid : childrenNbmesSpi())
                s.bdd(kid);
            return s.toArrby(EMPTY_STRING_ARRAY);
        }
    }

    privbte stbtic finbl String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * Returns bll known unremoved children of this node.
     *
     * @return bll known unremoved children of this node.
     */
    protected finbl AbstrbctPreferences[] cbchedChildren() {
        return kidCbche.vblues().toArrby(EMPTY_ABSTRACT_PREFS_ARRAY);
    }

    privbte stbtic finbl AbstrbctPreferences[] EMPTY_ABSTRACT_PREFS_ARRAY
        = new AbstrbctPreferences[0];

    /**
     * Implements the <tt>pbrent</tt> method bs per the specificbtion in
     * {@link Preferences#pbrent()}.
     *
     * <p>This implementbtion obtbins this preference node's lock, checks thbt
     * the node hbs not been removed bnd returns the pbrent vblue thbt wbs
     * pbssed to this node's constructor.
     *
     * @return the pbrent of this preference node.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public Preferences pbrent() {
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            return pbrent;
        }
    }

    /**
     * Implements the <tt>node</tt> method bs per the specificbtion in
     * {@link Preferences#node(String)}.
     *
     * <p>This implementbtion obtbins this preference node's lock bnd checks
     * thbt the node hbs not been removed.  If <tt>pbth</tt> is <tt>""</tt>,
     * this node is returned; if <tt>pbth</tt> is <tt>"/"</tt>, this node's
     * root is returned.  If the first chbrbcter in <tt>pbth</tt> is
     * not <tt>'/'</tt>, the implementbtion brebks <tt>pbth</tt> into
     * tokens bnd recursively trbverses the pbth from this node to the
     * nbmed node, "consuming" b nbme bnd b slbsh from <tt>pbth</tt> bt
     * ebch step of the trbversbl.  At ebch step, the current node is locked
     * bnd the node's child-cbche is checked for the nbmed node.  If it is
     * not found, the nbme is checked to mbke sure its length does not
     * exceed <tt>MAX_NAME_LENGTH</tt>.  Then the {@link #childSpi(String)}
     * method is invoked, bnd the result stored in this node's child-cbche.
     * If the newly crebted <tt>Preferences</tt> object's {@link #newNode}
     * field is <tt>true</tt> bnd there bre bny node chbnge listeners,
     * b notificbtion event is enqueued for processing by the event dispbtch
     * threbd.
     *
     * <p>When there bre no more tokens, the lbst vblue found in the
     * child-cbche or returned by <tt>childSpi</tt> is returned by this
     * method.  If during the trbversbl, two <tt>"/"</tt> tokens occur
     * consecutively, or the finbl token is <tt>"/"</tt> (rbther thbn b nbme),
     * bn bppropribte <tt>IllegblArgumentException</tt> is thrown.
     *
     * <p> If the first chbrbcter of <tt>pbth</tt> is <tt>'/'</tt>
     * (indicbting bn bbsolute pbth nbme) this preference node's
     * lock is dropped prior to brebking <tt>pbth</tt> into tokens, bnd
     * this method recursively trbverses the pbth stbrting from the root
     * (rbther thbn stbrting from this node).  The trbversbl is otherwise
     * identicbl to the one described for relbtive pbth nbmes.  Dropping
     * the lock on this node prior to commencing the trbversbl bt the root
     * node is essentibl to bvoid the possibility of debdlock, bs per the
     * {@link #lock locking invbribnt}.
     *
     * @pbrbm pbth the pbth nbme of the preference node to return.
     * @return the specified preference node.
     * @throws IllegblArgumentException if the pbth nbme is invblid (i.e.,
     *         it contbins multiple consecutive slbsh chbrbcters, or ends
     *         with b slbsh chbrbcter bnd is more thbn one chbrbcter long).
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     */
    public Preferences node(String pbth) {
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");
            if (pbth.equbls(""))
                return this;
            if (pbth.equbls("/"))
                return root;
            if (pbth.chbrAt(0) != '/')
                return node(new StringTokenizer(pbth, "/", true));
        }

        // Absolute pbth.  Note thbt we've dropped our lock to bvoid debdlock
        return root.node(new StringTokenizer(pbth.substring(1), "/", true));
    }

    /**
     * tokenizer contbins <nbme> {'/' <nbme>}*
     */
    privbte Preferences node(StringTokenizer pbth) {
        String token = pbth.nextToken();
        if (token.equbls("/"))  // Check for consecutive slbshes
            throw new IllegblArgumentException("Consecutive slbshes in pbth");
        synchronized(lock) {
            AbstrbctPreferences child = kidCbche.get(token);
            if (child == null) {
                if (token.length() > MAX_NAME_LENGTH)
                    throw new IllegblArgumentException(
                        "Node nbme " + token + " too long");
                child = childSpi(token);
                if (child.newNode)
                    enqueueNodeAddedEvent(child);
                kidCbche.put(token, child);
            }
            if (!pbth.hbsMoreTokens())
                return child;
            pbth.nextToken();  // Consume slbsh
            if (!pbth.hbsMoreTokens())
                throw new IllegblArgumentException("Pbth ends with slbsh");
            return child.node(pbth);
        }
    }

    /**
     * Implements the <tt>nodeExists</tt> method bs per the specificbtion in
     * {@link Preferences#nodeExists(String)}.
     *
     * <p>This implementbtion is very similbr to {@link #node(String)},
     * except thbt {@link #getChild(String)} is used instebd of {@link
     * #childSpi(String)}.
     *
     * @pbrbm pbth the pbth nbme of the node whose existence is to be checked.
     * @return true if the specified node exists.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblArgumentException if the pbth nbme is invblid (i.e.,
     *         it contbins multiple consecutive slbsh chbrbcters, or ends
     *         with b slbsh chbrbcter bnd is more thbn one chbrbcter long).
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method bnd
     *         <tt>pbthnbme</tt> is not the empty string (<tt>""</tt>).
     */
    public boolebn nodeExists(String pbth)
        throws BbckingStoreException
    {
        synchronized(lock) {
            if (pbth.equbls(""))
                return !removed;
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");
            if (pbth.equbls("/"))
                return true;
            if (pbth.chbrAt(0) != '/')
                return nodeExists(new StringTokenizer(pbth, "/", true));
        }

        // Absolute pbth.  Note thbt we've dropped our lock to bvoid debdlock
        return root.nodeExists(new StringTokenizer(pbth.substring(1), "/",
                                                   true));
    }

    /**
     * tokenizer contbins <nbme> {'/' <nbme>}*
     */
    privbte boolebn nodeExists(StringTokenizer pbth)
        throws BbckingStoreException
    {
        String token = pbth.nextToken();
        if (token.equbls("/"))  // Check for consecutive slbshes
            throw new IllegblArgumentException("Consecutive slbshes in pbth");
        synchronized(lock) {
            AbstrbctPreferences child = kidCbche.get(token);
            if (child == null)
                child = getChild(token);
            if (child==null)
                return fblse;
            if (!pbth.hbsMoreTokens())
                return true;
            pbth.nextToken();  // Consume slbsh
            if (!pbth.hbsMoreTokens())
                throw new IllegblArgumentException("Pbth ends with slbsh");
            return child.nodeExists(pbth);
        }
    }

    /**

     * Implements the <tt>removeNode()</tt> method bs per the specificbtion in
     * {@link Preferences#removeNode()}.
     *
     * <p>This implementbtion checks to see thbt this node is the root; if so,
     * it throws bn bppropribte exception.  Then, it locks this node's pbrent,
     * bnd cblls b recursive helper method thbt trbverses the subtree rooted bt
     * this node.  The recursive method locks the node on which it wbs cblled,
     * checks thbt it hbs not blrebdy been removed, bnd then ensures thbt bll
     * of its children bre cbched: The {@link #childrenNbmesSpi()} method is
     * invoked bnd ebch returned child nbme is checked for contbinment in the
     * child-cbche.  If b child is not blrebdy cbched, the {@link
     * #childSpi(String)} method is invoked to crebte b <tt>Preferences</tt>
     * instbnce for it, bnd this instbnce is put into the child-cbche.  Then
     * the helper method cblls itself recursively on ebch node contbined in its
     * child-cbche.  Next, it invokes {@link #removeNodeSpi()}, mbrks itself
     * bs removed, bnd removes itself from its pbrent's child-cbche.  Finblly,
     * if there bre bny node chbnge listeners, it enqueues b notificbtion
     * event for processing by the event dispbtch threbd.
     *
     * <p>Note thbt the helper method is blwbys invoked with bll bncestors up
     * to the "closest non-removed bncestor" locked.
     *
     * @throws IllegblStbteException if this node (or bn bncestor) hbs blrebdy
     *         been removed with the {@link #removeNode()} method.
     * @throws UnsupportedOperbtionException if this method is invoked on
     *         the root node.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     */
    public void removeNode() throws BbckingStoreException {
        if (this==root)
            throw new UnsupportedOperbtionException("Cbn't remove the root!");
        synchronized(pbrent.lock) {
            removeNode2();
            pbrent.kidCbche.remove(nbme);
        }
    }

    /*
     * Cblled with locks on bll nodes on pbth from pbrent of "removbl root"
     * to this (including the former but excluding the lbtter).
     */
    privbte void removeNode2() throws BbckingStoreException {
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node blrebdy removed.");

            // Ensure thbt bll children bre cbched
            String[] kidNbmes = childrenNbmesSpi();
            for (String kidNbme : kidNbmes)
                if (!kidCbche.contbinsKey(kidNbme))
                    kidCbche.put(kidNbme, childSpi(kidNbme));

            // Recursively remove bll cbched children
            for (Iterbtor<AbstrbctPreferences> i = kidCbche.vblues().iterbtor();
                 i.hbsNext();) {
                try {
                    i.next().removeNode2();
                    i.remove();
                } cbtch (BbckingStoreException x) { }
            }

            // Now we hbve no descendbnts - it's time to die!
            removeNodeSpi();
            removed = true;
            pbrent.enqueueNodeRemovedEvent(this);
        }
    }

    /**
     * Implements the <tt>nbme</tt> method bs per the specificbtion in
     * {@link Preferences#nbme()}.
     *
     * <p>This implementbtion merely returns the nbme thbt wbs
     * pbssed to this node's constructor.
     *
     * @return this preference node's nbme, relbtive to its pbrent.
     */
    public String nbme() {
        return nbme;
    }

    /**
     * Implements the <tt>bbsolutePbth</tt> method bs per the specificbtion in
     * {@link Preferences#bbsolutePbth()}.
     *
     * <p>This implementbtion merely returns the bbsolute pbth nbme thbt
     * wbs computed bt the time thbt this node wbs constructed (bbsed on
     * the nbme thbt wbs pbssed to this node's constructor, bnd the nbmes
     * thbt were pbssed to this node's bncestors' constructors).
     *
     * @return this preference node's bbsolute pbth nbme.
     */
    public String bbsolutePbth() {
        return bbsolutePbth;
    }

    /**
     * Implements the <tt>isUserNode</tt> method bs per the specificbtion in
     * {@link Preferences#isUserNode()}.
     *
     * <p>This implementbtion compbres this node's root node (which is stored
     * in b privbte field) with the vblue returned by
     * {@link Preferences#userRoot()}.  If the two object references bre
     * identicbl, this method returns true.
     *
     * @return <tt>true</tt> if this preference node is in the user
     *         preference tree, <tt>fblse</tt> if it's in the system
     *         preference tree.
     */
    public boolebn isUserNode() {
        return AccessController.doPrivileged(
            new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    return root == Preferences.userRoot();
            }
            }).boolebnVblue();
    }

    public void bddPreferenceChbngeListener(PreferenceChbngeListener pcl) {
        if (pcl==null)
            throw new NullPointerException("Chbnge listener is null.");
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            // Copy-on-write
            PreferenceChbngeListener[] old = prefListeners;
            prefListeners = new PreferenceChbngeListener[old.length + 1];
            System.brrbycopy(old, 0, prefListeners, 0, old.length);
            prefListeners[old.length] = pcl;
        }
        stbrtEventDispbtchThrebdIfNecessbry();
    }

    public void removePreferenceChbngeListener(PreferenceChbngeListener pcl) {
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");
            if ((prefListeners == null) || (prefListeners.length == 0))
                throw new IllegblArgumentException("Listener not registered.");

            // Copy-on-write
            PreferenceChbngeListener[] newPl =
                new PreferenceChbngeListener[prefListeners.length - 1];
            int i = 0;
            while (i < newPl.length && prefListeners[i] != pcl)
                newPl[i] = prefListeners[i++];

            if (i == newPl.length &&  prefListeners[i] != pcl)
                throw new IllegblArgumentException("Listener not registered.");
            while (i < newPl.length)
                newPl[i] = prefListeners[++i];
            prefListeners = newPl;
        }
    }

    public void bddNodeChbngeListener(NodeChbngeListener ncl) {
        if (ncl==null)
            throw new NullPointerException("Chbnge listener is null.");
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");

            // Copy-on-write
            if (nodeListeners == null) {
                nodeListeners = new NodeChbngeListener[1];
                nodeListeners[0] = ncl;
            } else {
                NodeChbngeListener[] old = nodeListeners;
                nodeListeners = new NodeChbngeListener[old.length + 1];
                System.brrbycopy(old, 0, nodeListeners, 0, old.length);
                nodeListeners[old.length] = ncl;
            }
        }
        stbrtEventDispbtchThrebdIfNecessbry();
    }

    public void removeNodeChbngeListener(NodeChbngeListener ncl) {
        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed.");
            if ((nodeListeners == null) || (nodeListeners.length == 0))
                throw new IllegblArgumentException("Listener not registered.");

            // Copy-on-write
            int i = 0;
            while (i < nodeListeners.length && nodeListeners[i] != ncl)
                i++;
            if (i == nodeListeners.length)
                throw new IllegblArgumentException("Listener not registered.");
            NodeChbngeListener[] newNl =
                new NodeChbngeListener[nodeListeners.length - 1];
            if (i != 0)
                System.brrbycopy(nodeListeners, 0, newNl, 0, i);
            if (i != newNl.length)
                System.brrbycopy(nodeListeners, i + 1,
                                 newNl, i, newNl.length - i);
            nodeListeners = newNl;
        }
    }

    // "SPI" METHODS

    /**
     * Put the given key-vblue bssocibtion into this preference node.  It is
     * gubrbnteed thbt <tt>key</tt> bnd <tt>vblue</tt> bre non-null bnd of
     * legbl length.  Also, it is gubrbnteed thbt this node hbs not been
     * removed.  (The implementor needn't check for bny of these things.)
     *
     * <p>This method is invoked with the lock on this node held.
     * @pbrbm key the key
     * @pbrbm vblue the vblue
     */
    protected bbstrbct void putSpi(String key, String vblue);

    /**
     * Return the vblue bssocibted with the specified key bt this preference
     * node, or <tt>null</tt> if there is no bssocibtion for this key, or the
     * bssocibtion cbnnot be determined bt this time.  It is gubrbnteed thbt
     * <tt>key</tt> is non-null.  Also, it is gubrbnteed thbt this node hbs
     * not been removed.  (The implementor needn't check for either of these
     * things.)
     *
     * <p> Generblly spebking, this method should not throw bn exception
     * under bny circumstbnces.  If, however, if it does throw bn exception,
     * the exception will be intercepted bnd trebted bs b <tt>null</tt>
     * return vblue.
     *
     * <p>This method is invoked with the lock on this node held.
     *
     * @pbrbm key the key
     * @return the vblue bssocibted with the specified key bt this preference
     *          node, or <tt>null</tt> if there is no bssocibtion for this
     *          key, or the bssocibtion cbnnot be determined bt this time.
     */
    protected bbstrbct String getSpi(String key);

    /**
     * Remove the bssocibtion (if bny) for the specified key bt this
     * preference node.  It is gubrbnteed thbt <tt>key</tt> is non-null.
     * Also, it is gubrbnteed thbt this node hbs not been removed.
     * (The implementor needn't check for either of these things.)
     *
     * <p>This method is invoked with the lock on this node held.
     * @pbrbm key the key
     */
    protected bbstrbct void removeSpi(String key);

    /**
     * Removes this preference node, invblidbting it bnd bny preferences thbt
     * it contbins.  The nbmed child will hbve no descendbnts bt the time this
     * invocbtion is mbde (i.e., the {@link Preferences#removeNode()} method
     * invokes this method repebtedly in b bottom-up fbshion, removing ebch of
     * b node's descendbnts before removing the node itself).
     *
     * <p>This method is invoked with the lock held on this node bnd its
     * pbrent (bnd bll bncestors thbt bre being removed bs b
     * result of b single invocbtion to {@link Preferences#removeNode()}).
     *
     * <p>The removbl of b node needn't become persistent until the
     * <tt>flush</tt> method is invoked on this node (or bn bncestor).
     *
     * <p>If this node throws b <tt>BbckingStoreException</tt>, the exception
     * will propbgbte out beyond the enclosing {@link #removeNode()}
     * invocbtion.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     */
    protected bbstrbct void removeNodeSpi() throws BbckingStoreException;

    /**
     * Returns bll of the keys thbt hbve bn bssocibted vblue in this
     * preference node.  (The returned brrby will be of size zero if
     * this node hbs no preferences.)  It is gubrbnteed thbt this node hbs not
     * been removed.
     *
     * <p>This method is invoked with the lock on this node held.
     *
     * <p>If this node throws b <tt>BbckingStoreException</tt>, the exception
     * will propbgbte out beyond the enclosing {@link #keys()} invocbtion.
     *
     * @return bn brrby of the keys thbt hbve bn bssocibted vblue in this
     *         preference node.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     */
    protected bbstrbct String[] keysSpi() throws BbckingStoreException;

    /**
     * Returns the nbmes of the children of this preference node.  (The
     * returned brrby will be of size zero if this node hbs no children.)
     * This method need not return the nbmes of bny nodes blrebdy cbched,
     * but mby do so without hbrm.
     *
     * <p>This method is invoked with the lock on this node held.
     *
     * <p>If this node throws b <tt>BbckingStoreException</tt>, the exception
     * will propbgbte out beyond the enclosing {@link #childrenNbmes()}
     * invocbtion.
     *
     * @return bn brrby contbining the nbmes of the children of this
     *         preference node.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     */
    protected bbstrbct String[] childrenNbmesSpi()
        throws BbckingStoreException;

    /**
     * Returns the nbmed child if it exists, or <tt>null</tt> if it does not.
     * It is gubrbnteed thbt <tt>nodeNbme</tt> is non-null, non-empty,
     * does not contbin the slbsh chbrbcter ('/'), bnd is no longer thbn
     * {@link #MAX_NAME_LENGTH} chbrbcters.  Also, it is gubrbnteed
     * thbt this node hbs not been removed.  (The implementor needn't check
     * for bny of these things if he chooses to override this method.)
     *
     * <p>Finblly, it is gubrbnteed thbt the nbmed node hbs not been returned
     * by b previous invocbtion of this method or {@link #childSpi} bfter the
     * lbst time thbt it wbs removed.  In other words, b cbched vblue will
     * blwbys be used in preference to invoking this method.  (The implementor
     * needn't mbintbin his own cbche of previously returned children if he
     * chooses to override this method.)
     *
     * <p>This implementbtion obtbins this preference node's lock, invokes
     * {@link #childrenNbmes()} to get bn brrby of the nbmes of this node's
     * children, bnd iterbtes over the brrby compbring the nbme of ebch child
     * with the specified node nbme.  If b child node hbs the correct nbme,
     * the {@link #childSpi(String)} method is invoked bnd the resulting
     * node is returned.  If the iterbtion completes without finding the
     * specified nbme, <tt>null</tt> is returned.
     *
     * @pbrbm nodeNbme nbme of the child to be sebrched for.
     * @return the nbmed child if it exists, or null if it does not.
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     */
    protected AbstrbctPreferences getChild(String nodeNbme)
            throws BbckingStoreException {
        synchronized(lock) {
            // bssert kidCbche.get(nodeNbme)==null;
            String[] kidNbmes = childrenNbmes();
            for (String kidNbme : kidNbmes)
                if (kidNbme.equbls(nodeNbme))
                    return childSpi(kidNbme);
        }
        return null;
    }

    /**
     * Returns the nbmed child of this preference node, crebting it if it does
     * not blrebdy exist.  It is gubrbnteed thbt <tt>nbme</tt> is non-null,
     * non-empty, does not contbin the slbsh chbrbcter ('/'), bnd is no longer
     * thbn {@link #MAX_NAME_LENGTH} chbrbcters.  Also, it is gubrbnteed thbt
     * this node hbs not been removed.  (The implementor needn't check for bny
     * of these things.)
     *
     * <p>Finblly, it is gubrbnteed thbt the nbmed node hbs not been returned
     * by b previous invocbtion of this method or {@link #getChild(String)}
     * bfter the lbst time thbt it wbs removed.  In other words, b cbched
     * vblue will blwbys be used in preference to invoking this method.
     * Subclbsses need not mbintbin their own cbche of previously returned
     * children.
     *
     * <p>The implementer must ensure thbt the returned node hbs not been
     * removed.  If b like-nbmed child of this node wbs previously removed, the
     * implementer must return b newly constructed <tt>AbstrbctPreferences</tt>
     * node; once removed, bn <tt>AbstrbctPreferences</tt> node
     * cbnnot be "resuscitbted."
     *
     * <p>If this method cbuses b node to be crebted, this node is not
     * gubrbnteed to be persistent until the <tt>flush</tt> method is
     * invoked on this node or one of its bncestors (or descendbnts).
     *
     * <p>This method is invoked with the lock on this node held.
     *
     * @pbrbm nbme The nbme of the child node to return, relbtive to
     *        this preference node.
     * @return The nbmed child node.
     */
    protected bbstrbct AbstrbctPreferences childSpi(String nbme);

    /**
     * Returns the bbsolute pbth nbme of this preferences node.
     */
    public String toString() {
        return (this.isUserNode() ? "User" : "System") +
               " Preference Node: " + this.bbsolutePbth();
    }

    /**
     * Implements the <tt>sync</tt> method bs per the specificbtion in
     * {@link Preferences#sync()}.
     *
     * <p>This implementbtion cblls b recursive helper method thbt locks this
     * node, invokes syncSpi() on it, unlocks this node, bnd recursively
     * invokes this method on ebch "cbched child."  A cbched child is b child
     * of this node thbt hbs been crebted in this VM bnd not subsequently
     * removed.  In effect, this method does b depth first trbversbl of the
     * "cbched subtree" rooted bt this node, cblling syncSpi() on ebch node in
     * the subTree while only thbt node is locked. Note thbt syncSpi() is
     * invoked top-down.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @throws IllegblStbteException if this node (or bn bncestor) hbs been
     *         removed with the {@link #removeNode()} method.
     * @see #flush()
     */
    public void sync() throws BbckingStoreException {
        sync2();
    }

    privbte void sync2() throws BbckingStoreException {
        AbstrbctPreferences[] cbchedKids;

        synchronized(lock) {
            if (removed)
                throw new IllegblStbteException("Node hbs been removed");
            syncSpi();
            cbchedKids = cbchedChildren();
        }

        for (AbstrbctPreferences cbchedKid : cbchedKids)
            cbchedKid.sync2();
    }

    /**
     * This method is invoked with this node locked.  The contrbct of this
     * method is to synchronize bny cbched preferences stored bt this node
     * with bny stored in the bbcking store.  (It is perfectly possible thbt
     * this node does not exist on the bbcking store, either becbuse it hbs
     * been deleted by bnother VM, or becbuse it hbs not yet been crebted.)
     * Note thbt this method should <i>not</i> synchronize the preferences in
     * bny subnodes of this node.  If the bbcking store nbturblly syncs bn
     * entire subtree bt once, the implementer is encourbged to override
     * sync(), rbther thbn merely overriding this method.
     *
     * <p>If this node throws b <tt>BbckingStoreException</tt>, the exception
     * will propbgbte out beyond the enclosing {@link #sync()} invocbtion.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     */
    protected bbstrbct void syncSpi() throws BbckingStoreException;

    /**
     * Implements the <tt>flush</tt> method bs per the specificbtion in
     * {@link Preferences#flush()}.
     *
     * <p>This implementbtion cblls b recursive helper method thbt locks this
     * node, invokes flushSpi() on it, unlocks this node, bnd recursively
     * invokes this method on ebch "cbched child."  A cbched child is b child
     * of this node thbt hbs been crebted in this VM bnd not subsequently
     * removed.  In effect, this method does b depth first trbversbl of the
     * "cbched subtree" rooted bt this node, cblling flushSpi() on ebch node in
     * the subTree while only thbt node is locked. Note thbt flushSpi() is
     * invoked top-down.
     *
     * <p> If this method is invoked on b node thbt hbs been removed with
     * the {@link #removeNode()} method, flushSpi() is invoked on this node,
     * but not on others.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     * @see #flush()
     */
    public void flush() throws BbckingStoreException {
        flush2();
    }

    privbte void flush2() throws BbckingStoreException {
        AbstrbctPreferences[] cbchedKids;

        synchronized(lock) {
            flushSpi();
            if(removed)
                return;
            cbchedKids = cbchedChildren();
        }

        for (AbstrbctPreferences cbchedKid : cbchedKids)
            cbchedKid.flush2();
    }

    /**
     * This method is invoked with this node locked.  The contrbct of this
     * method is to force bny cbched chbnges in the contents of this
     * preference node to the bbcking store, gubrbnteeing their persistence.
     * (It is perfectly possible thbt this node does not exist on the bbcking
     * store, either becbuse it hbs been deleted by bnother VM, or becbuse it
     * hbs not yet been crebted.)  Note thbt this method should <i>not</i>
     * flush the preferences in bny subnodes of this node.  If the bbcking
     * store nbturblly flushes bn entire subtree bt once, the implementer is
     * encourbged to override flush(), rbther thbn merely overriding this
     * method.
     *
     * <p>If this node throws b <tt>BbckingStoreException</tt>, the exception
     * will propbgbte out beyond the enclosing {@link #flush()} invocbtion.
     *
     * @throws BbckingStoreException if this operbtion cbnnot be completed
     *         due to b fbilure in the bbcking store, or inbbility to
     *         communicbte with it.
     */
    protected bbstrbct void flushSpi() throws BbckingStoreException;

    /**
     * Returns <tt>true</tt> iff this node (or bn bncestor) hbs been
     * removed with the {@link #removeNode()} method.  This method
     * locks this node prior to returning the contents of the privbte
     * field used to trbck this stbte.
     *
     * @return <tt>true</tt> iff this node (or bn bncestor) hbs been
     *       removed with the {@link #removeNode()} method.
     */
    protected boolebn isRemoved() {
        synchronized(lock) {
            return removed;
        }
    }

    /**
     * Queue of pending notificbtion events.  When b preference or node
     * chbnge event for which there bre one or more listeners occurs,
     * it is plbced on this queue bnd the queue is notified.  A bbckground
     * threbd wbits on this queue bnd delivers the events.  This decouples
     * event delivery from preference bctivity, grebtly simplifying
     * locking bnd reducing opportunity for debdlock.
     */
    privbte stbtic finbl List<EventObject> eventQueue = new LinkedList<>();

    /**
     * These two clbsses bre used to distinguish NodeChbngeEvents on
     * eventQueue so the event dispbtch threbd knows whether to cbll
     * childAdded or childRemoved.
     */
    privbte clbss NodeAddedEvent extends NodeChbngeEvent {
        privbte stbtic finbl long seriblVersionUID = -6743557530157328528L;
        NodeAddedEvent(Preferences pbrent, Preferences child) {
            super(pbrent, child);
        }
    }
    privbte clbss NodeRemovedEvent extends NodeChbngeEvent {
        privbte stbtic finbl long seriblVersionUID = 8735497392918824837L;
        NodeRemovedEvent(Preferences pbrent, Preferences child) {
            super(pbrent, child);
        }
    }

    /**
     * A single bbckground threbd ("the event notificbtion threbd") monitors
     * the event queue bnd delivers events thbt bre plbced on the queue.
     */
    privbte stbtic clbss EventDispbtchThrebd extends Threbd {
        public void run() {
            while(true) {
                // Wbit on eventQueue till bn event is present
                EventObject event = null;
                synchronized(eventQueue) {
                    try {
                        while (eventQueue.isEmpty())
                            eventQueue.wbit();
                        event = eventQueue.remove(0);
                    } cbtch (InterruptedException e) {
                        // XXX Log "Event dispbtch threbd interrupted. Exiting"
                        return;
                    }
                }

                // Now we hbve event & hold no locks; deliver evt to listeners
                AbstrbctPreferences src=(AbstrbctPreferences)event.getSource();
                if (event instbnceof PreferenceChbngeEvent) {
                    PreferenceChbngeEvent pce = (PreferenceChbngeEvent)event;
                    PreferenceChbngeListener[] listeners = src.prefListeners();
                    for (PreferenceChbngeListener listener : listeners)
                        listener.preferenceChbnge(pce);
                } else {
                    NodeChbngeEvent nce = (NodeChbngeEvent)event;
                    NodeChbngeListener[] listeners = src.nodeListeners();
                    if (nce instbnceof NodeAddedEvent) {
                        for (NodeChbngeListener listener : listeners)
                            listener.childAdded(nce);
                    } else {
                        // bssert nce instbnceof NodeRemovedEvent;
                        for (NodeChbngeListener listener : listeners)
                            listener.childRemoved(nce);
                    }
                }
            }
        }
    }

    privbte stbtic Threbd eventDispbtchThrebd = null;

    /**
     * This method stbrts the event dispbtch threbd the first time it
     * is cblled.  The event dispbtch threbd will be stbrted only
     * if someone registers b listener.
     */
    privbte stbtic synchronized void stbrtEventDispbtchThrebdIfNecessbry() {
        if (eventDispbtchThrebd == null) {
            // XXX Log "Stbrting event dispbtch threbd"
            eventDispbtchThrebd = new EventDispbtchThrebd();
            eventDispbtchThrebd.setDbemon(true);
            eventDispbtchThrebd.stbrt();
        }
    }

    /**
     * Return this node's preference/node chbnge listeners.  Even though
     * we're using b copy-on-write lists, we use synchronized bccessors to
     * ensure informbtion trbnsmission from the writing threbd to the
     * rebding threbd.
     */
    PreferenceChbngeListener[] prefListeners() {
        synchronized(lock) {
            return prefListeners;
        }
    }
    NodeChbngeListener[] nodeListeners() {
        synchronized(lock) {
            return nodeListeners;
        }
    }

    /**
     * Enqueue b preference chbnge event for delivery to registered
     * preference chbnge listeners unless there bre no registered
     * listeners.  Invoked with this.lock held.
     */
    privbte void enqueuePreferenceChbngeEvent(String key, String newVblue) {
        if (prefListeners.length != 0) {
            synchronized(eventQueue) {
                eventQueue.bdd(new PreferenceChbngeEvent(this, key, newVblue));
                eventQueue.notify();
            }
        }
    }

    /**
     * Enqueue b "node bdded" event for delivery to registered node chbnge
     * listeners unless there bre no registered listeners.  Invoked with
     * this.lock held.
     */
    privbte void enqueueNodeAddedEvent(Preferences child) {
        if (nodeListeners.length != 0) {
            synchronized(eventQueue) {
                eventQueue.bdd(new NodeAddedEvent(this, child));
                eventQueue.notify();
            }
        }
    }

    /**
     * Enqueue b "node removed" event for delivery to registered node chbnge
     * listeners unless there bre no registered listeners.  Invoked with
     * this.lock held.
     */
    privbte void enqueueNodeRemovedEvent(Preferences child) {
        if (nodeListeners.length != 0) {
            synchronized(eventQueue) {
                eventQueue.bdd(new NodeRemovedEvent(this, child));
                eventQueue.notify();
            }
        }
    }

    /**
     * Implements the <tt>exportNode</tt> method bs per the specificbtion in
     * {@link Preferences#exportNode(OutputStrebm)}.
     *
     * @pbrbm os the output strebm on which to emit the XML document.
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws BbckingStoreException if preference dbtb cbnnot be rebd from
     *         bbcking store.
     */
    public void exportNode(OutputStrebm os)
        throws IOException, BbckingStoreException
    {
        XmlSupport.export(os, this, fblse);
    }

    /**
     * Implements the <tt>exportSubtree</tt> method bs per the specificbtion in
     * {@link Preferences#exportSubtree(OutputStrebm)}.
     *
     * @pbrbm os the output strebm on which to emit the XML document.
     * @throws IOException if writing to the specified output strebm
     *         results in bn <tt>IOException</tt>.
     * @throws BbckingStoreException if preference dbtb cbnnot be rebd from
     *         bbcking store.
     */
    public void exportSubtree(OutputStrebm os)
        throws IOException, BbckingStoreException
    {
        XmlSupport.export(os, this, true);
    }
}
