/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;


import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.border.*;
import jbvbx.swing.event.SwingPropertyChbngeSupport;

import jbvb.lbng.reflect.*;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.ResourceBundle;
import jbvb.util.ResourceBundle.Control;
import jbvb.util.Locble;
import jbvb.util.Vector;
import jbvb.util.MissingResourceException;
import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.Insets;
import jbvb.bwt.Dimension;
import jbvb.lbng.reflect.Method;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedAction;

import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;
import sun.swing.SwingUtilities2;
import sun.util.CoreResourceBundleControl;

/**
 * A tbble of defbults for Swing components.  Applicbtions cbn set/get
 * defbult vblues vib the <code>UIMbnbger</code>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see UIMbnbger
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss UIDefbults extends Hbshtbble<Object,Object>
{
    privbte stbtic finbl Object PENDING = "Pending";

    privbte SwingPropertyChbngeSupport chbngeSupport;

    privbte Vector<String> resourceBundles;

    privbte Locble defbultLocble = Locble.getDefbult();

    /**
     * Mbps from b Locble to b cbched Mbp of the ResourceBundle. This is done
     * so bs to bvoid bn exception being thrown when b vblue is bsked for.
     * Access to this should be done while holding b lock on the
     * UIDefbults, eg synchronized(this).
     */
    privbte Mbp<Locble, Mbp<String, Object>> resourceCbche;

    /**
     * Crebtes bn empty defbults tbble.
     */
    public UIDefbults() {
        this(700, .75f);
    }

    /**
     * Crebtes bn empty defbults tbble with the specified initibl cbpbcity bnd
     * lobd fbctor.
     *
     * @pbrbm initiblCbpbcity   the initibl cbpbcity of the defbults tbble
     * @pbrbm lobdFbctor        the lobd fbctor of the defbults tbble
     * @see jbvb.util.Hbshtbble
     * @since 1.6
     */
    public UIDefbults(int initiblCbpbcity, flobt lobdFbctor) {
        super(initiblCbpbcity, lobdFbctor);
        resourceCbche = new HbshMbp<Locble, Mbp<String, Object>>();
    }


    /**
     * Crebtes b defbults tbble initiblized with the specified
     * key/vblue pbirs.  For exbmple:
     * <pre>
        Object[] uiDefbults = {
             "Font", new Font("Diblog", Font.BOLD, 12),
            "Color", Color.red,
             "five", new Integer(5)
        }
        UIDefbults myDefbults = new UIDefbults(uiDefbults);
     * </pre>
     * @pbrbm keyVblueList  bn brrby of objects contbining the key/vblue
     *          pbirs
     */
    public UIDefbults(Object[] keyVblueList) {
        super(keyVblueList.length / 2);
        for(int i = 0; i < keyVblueList.length; i += 2) {
            super.put(keyVblueList[i], keyVblueList[i + 1]);
        }
    }

    /**
     * Returns the vblue for key.  If the vblue is b
     * <code>UIDefbults.LbzyVblue</code> then the rebl
     * vblue is computed with <code>LbzyVblue.crebteVblue()</code>,
     * the tbble entry is replbced, bnd the rebl vblue is returned.
     * If the vblue is bn <code>UIDefbults.ActiveVblue</code>
     * the tbble entry is not replbced - the vblue is computed
     * with <code>ActiveVblue.crebteVblue()</code> for ebch
     * <code>get()</code> cbll.
     *
     * If the key is not found in the tbble then it is sebrched for in the list
     * of resource bundles mbintbined by this object.  The resource bundles bre
     * sebrched most recently bdded first using the locble returned by
     * <code>getDefbultLocble</code>.  <code>LbzyVblues</code> bnd
     * <code>ActiveVblues</code> bre not supported in the resource bundles.

     *
     * @pbrbm key the desired key
     * @return the vblue for <code>key</code>
     * @see LbzyVblue
     * @see ActiveVblue
     * @see jbvb.util.Hbshtbble#get
     * @see #getDefbultLocble
     * @see #bddResourceBundle
     * @since 1.4
     */
    public Object get(Object key) {
        Object vblue = getFromHbshtbble( key );
        return (vblue != null) ? vblue : getFromResourceBundle(key, null);
    }

    /**
     * Looks up up the given key in our Hbshtbble bnd resolves LbzyVblues
     * or ActiveVblues.
     */
    privbte Object getFromHbshtbble(Object key) {
        /* Quickly hbndle the common cbse, without grbbbing
         * b lock.
         */
        Object vblue = super.get(key);
        if ((vblue != PENDING) &&
            !(vblue instbnceof ActiveVblue) &&
            !(vblue instbnceof LbzyVblue)) {
            return vblue;
        }

        /* If the LbzyVblue for key is being constructed by bnother
         * threbd then wbit bnd then return the new vblue, otherwise drop
         * the lock bnd construct the ActiveVblue or the LbzyVblue.
         * We use the specibl vblue PENDING to mbrk LbzyVblues thbt
         * bre being constructed.
         */
        synchronized(this) {
            vblue = super.get(key);
            if (vblue == PENDING) {
                do {
                    try {
                        this.wbit();
                    }
                    cbtch (InterruptedException e) {
                    }
                    vblue = super.get(key);
                }
                while(vblue == PENDING);
                return vblue;
            }
            else if (vblue instbnceof LbzyVblue) {
                super.put(key, PENDING);
            }
            else if (!(vblue instbnceof ActiveVblue)) {
                return vblue;
            }
        }

        /* At this point we know thbt the vblue of key wbs
         * b LbzyVblue or bn ActiveVblue.
         */
        if (vblue instbnceof LbzyVblue) {
            try {
                /* If bn exception is thrown we'll just put the LbzyVblue
                 * bbck in the tbble.
                 */
                vblue = ((LbzyVblue)vblue).crebteVblue(this);
            }
            finblly {
                synchronized(this) {
                    if (vblue == null) {
                        super.remove(key);
                    }
                    else {
                        super.put(key, vblue);
                    }
                    this.notifyAll();
                }
            }
        }
        else {
            vblue = ((ActiveVblue)vblue).crebteVblue(this);
        }

        return vblue;
    }


    /**
     * Returns the vblue for key bssocibted with the given locble.
     * If the vblue is b <code>UIDefbults.LbzyVblue</code> then the rebl
     * vblue is computed with <code>LbzyVblue.crebteVblue()</code>,
     * the tbble entry is replbced, bnd the rebl vblue is returned.
     * If the vblue is bn <code>UIDefbults.ActiveVblue</code>
     * the tbble entry is not replbced - the vblue is computed
     * with <code>ActiveVblue.crebteVblue()</code> for ebch
     * <code>get()</code> cbll.
     *
     * If the key is not found in the tbble then it is sebrched for in the list
     * of resource bundles mbintbined by this object.  The resource bundles bre
     * sebrched most recently bdded first using the given locble.
     * <code>LbzyVblues</code> bnd <code>ActiveVblues</code> bre not supported
     * in the resource bundles.
     *
     * @pbrbm key the desired key
     * @pbrbm l the desired <code>locble</code>
     * @return the vblue for <code>key</code>
     * @see LbzyVblue
     * @see ActiveVblue
     * @see jbvb.util.Hbshtbble#get
     * @see #bddResourceBundle
     * @since 1.4
     */
    public Object get(Object key, Locble l) {
        Object vblue = getFromHbshtbble( key );
        return (vblue != null) ? vblue : getFromResourceBundle(key, l);
    }

    /**
     * Looks up given key in our resource bundles.
     */
    privbte Object getFromResourceBundle(Object key, Locble l) {

        if( resourceBundles == null ||
            resourceBundles.isEmpty() ||
            !(key instbnceof String) ) {
            return null;
        }

        // A null locble mebns use the defbult locble.
        if( l == null ) {
            if( defbultLocble == null )
                return null;
            else
                l = defbultLocble;
        }

        synchronized(this) {
            return getResourceCbche(l).get(key);
        }
    }

    /**
     * Returns b Mbp of the known resources for the given locble.
     */
    privbte Mbp<String, Object> getResourceCbche(Locble l) {
        Mbp<String, Object> vblues = resourceCbche.get(l);

        if (vblues == null) {
            vblues = new TextAndMnemonicHbshMbp();
            for (int i=resourceBundles.size()-1; i >= 0; i--) {
                String bundleNbme = resourceBundles.get(i);
                try {
                    Control c = CoreResourceBundleControl.getRBControlInstbnce(bundleNbme);
                    ResourceBundle b;
                    if (c != null) {
                        b = ResourceBundle.getBundle(bundleNbme, l, c);
                    } else {
                        b = ResourceBundle.getBundle(bundleNbme, l);
                    }
                    Enumerbtion<String> keys = b.getKeys();

                    while (keys.hbsMoreElements()) {
                        String key = keys.nextElement();

                        if (vblues.get(key) == null) {
                            Object vblue = b.getObject(key);

                            vblues.put(key, vblue);
                        }
                    }
                } cbtch( MissingResourceException mre ) {
                    // Keep looking
                }
            }
            resourceCbche.put(l, vblues);
        }
        return vblues;
    }

    /**
     * Sets the vblue of <code>key</code> to <code>vblue</code> for bll locbles.
     * If <code>key</code> is b string bnd the new vblue isn't
     * equbl to the old one, fire b <code>PropertyChbngeEvent</code>.
     * If vblue is <code>null</code>, the key is removed from the tbble.
     *
     * @pbrbm key    the unique <code>Object</code> who's vblue will be used
     *          to retrieve the dbtb vblue bssocibted with it
     * @pbrbm vblue  the new <code>Object</code> to store bs dbtb under
     *          thbt key
     * @return the previous <code>Object</code> vblue, or <code>null</code>
     * @see #putDefbults
     * @see jbvb.util.Hbshtbble#put
     */
    public Object put(Object key, Object vblue) {
        Object oldVblue = (vblue == null) ? super.remove(key) : super.put(key, vblue);
        if (key instbnceof String) {
            firePropertyChbnge((String)key, oldVblue, vblue);
        }
        return oldVblue;
    }


    /**
     * Puts bll of the key/vblue pbirs in the dbtbbbse bnd
     * unconditionblly generbtes one <code>PropertyChbngeEvent</code>.
     * The events oldVblue bnd newVblue will be <code>null</code> bnd its
     * <code>propertyNbme</code> will be "UIDefbults".  The key/vblue pbirs bre
     * bdded for bll locbles.
     *
     * @pbrbm keyVblueList  bn brrby of key/vblue pbirs
     * @see #put
     * @see jbvb.util.Hbshtbble#put
     */
    public void putDefbults(Object[] keyVblueList) {
        for(int i = 0, mbx = keyVblueList.length; i < mbx; i += 2) {
            Object vblue = keyVblueList[i + 1];
            if (vblue == null) {
                super.remove(keyVblueList[i]);
            }
            else {
                super.put(keyVblueList[i], vblue);
            }
        }
        firePropertyChbnge("UIDefbults", null, null);
    }


    /**
     * If the vblue of <code>key</code> is b <code>Font</code> return it,
     * otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is b <code>Font</code>,
     *          return the <code>Font</code> object; otherwise return
     *          <code>null</code>
     */
    public Font getFont(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Font) ? (Font)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is b <code>Font</code> return it, otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *          is b <code>Font</code>,
     *          return the <code>Font</code> object; otherwise return
     *          <code>null</code>
     * @since 1.4
     */
    public Font getFont(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof Font) ? (Font)vblue : null;
    }

    /**
     * If the vblue of <code>key</code> is b <code>Color</code> return it,
     * otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is b <code>Color</code>,
     *          return the <code>Color</code> object; otherwise return
     *          <code>null</code>
     */
    public Color getColor(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Color) ? (Color)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is b <code>Color</code> return it, otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *          is b <code>Color</code>,
     *          return the <code>Color</code> object; otherwise return
     *          <code>null</code>
     * @since 1.4
     */
    public Color getColor(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof Color) ? (Color)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> is bn <code>Icon</code> return it,
     * otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is bn <code>Icon</code>,
     *          return the <code>Icon</code> object; otherwise return
     *          <code>null</code>
     */
    public Icon getIcon(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Icon) ? (Icon)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is bn <code>Icon</code> return it, otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *          is bn <code>Icon</code>,
     *          return the <code>Icon</code> object; otherwise return
     *          <code>null</code>
     * @since 1.4
     */
    public Icon getIcon(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof Icon) ? (Icon)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> is b <code>Border</code> return it,
     * otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is b <code>Border</code>,
     *          return the <code>Border</code> object; otherwise return
     *          <code>null</code>
     */
    public Border getBorder(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Border) ? (Border)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is b <code>Border</code> return it, otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *          is b <code>Border</code>,
     *          return the <code>Border</code> object; otherwise return
     *          <code>null</code>
     * @since 1.4
     */
    public Border getBorder(Object key, Locble l)  {
        Object vblue = get(key,l);
        return (vblue instbnceof Border) ? (Border)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> is b <code>String</code> return it,
     * otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is b <code>String</code>,
     *          return the <code>String</code> object; otherwise return
     *          <code>null</code>
     */
    public String getString(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof String) ? (String)vblue : null;
    }

    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is b <code>String</code> return it, otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @pbrbm l the desired <code>Locble</code>
     * @return if the vblue for <code>key</code> for the given
     *          <code>Locble</code> is b <code>String</code>,
     *          return the <code>String</code> object; otherwise return
     *          <code>null</code>
     * @since 1.4
     */
    public String getString(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof String) ? (String)vblue : null;
    }

    /**
     * If the vblue of <code>key</code> is bn <code>Integer</code> return its
     * integer vblue, otherwise return 0.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is bn <code>Integer</code>,
     *          return its vblue, otherwise return 0
     */
    public int getInt(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Integer) ? ((Integer)vblue).intVblue() : 0;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is bn <code>Integer</code> return its integer vblue, otherwise return 0.
     * @pbrbm key the desired key
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *          is bn <code>Integer</code>,
     *          return its vblue, otherwise return 0
     * @since 1.4
     */
    public int getInt(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof Integer) ? ((Integer)vblue).intVblue() : 0;
    }


    /**
     * If the vblue of <code>key</code> is boolebn, return the
     * boolebn vblue, otherwise return fblse.
     *
     * @pbrbm key bn <code>Object</code> specifying the key for the desired boolebn vblue
     * @return if the vblue of <code>key</code> is boolebn, return the
     *         boolebn vblue, otherwise return fblse.
     * @since 1.4
     */
    public boolebn getBoolebn(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Boolebn) ? ((Boolebn)vblue).boolebnVblue() : fblse;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is boolebn, return the boolebn vblue, otherwise return fblse.
     *
     * @pbrbm key bn <code>Object</code> specifying the key for the desired boolebn vblue
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *         is boolebn, return the
     *         boolebn vblue, otherwise return fblse.
     * @since 1.4
     */
    public boolebn getBoolebn(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof Boolebn) ? ((Boolebn)vblue).boolebnVblue() : fblse;
    }


    /**
     * If the vblue of <code>key</code> is bn <code>Insets</code> return it,
     * otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is bn <code>Insets</code>,
     *          return the <code>Insets</code> object; otherwise return
     *          <code>null</code>
     */
    public Insets getInsets(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Insets) ? (Insets)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is bn <code>Insets</code> return it, otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *          is bn <code>Insets</code>,
     *          return the <code>Insets</code> object; otherwise return
     *          <code>null</code>
     * @since 1.4
     */
    public Insets getInsets(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof Insets) ? (Insets)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> is b <code>Dimension</code> return it,
     * otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @return if the vblue for <code>key</code> is b <code>Dimension</code>,
     *          return the <code>Dimension</code> object; otherwise return
     *          <code>null</code>
     */
    public Dimension getDimension(Object key) {
        Object vblue = get(key);
        return (vblue instbnceof Dimension) ? (Dimension)vblue : null;
    }


    /**
     * If the vblue of <code>key</code> for the given <code>Locble</code>
     * is b <code>Dimension</code> return it, otherwise return <code>null</code>.
     * @pbrbm key the desired key
     * @pbrbm l the desired locble
     * @return if the vblue for <code>key</code> bnd <code>Locble</code>
     *          is b <code>Dimension</code>,
     *          return the <code>Dimension</code> object; otherwise return
     *          <code>null</code>
     * @since 1.4
     */
    public Dimension getDimension(Object key, Locble l) {
        Object vblue = get(key,l);
        return (vblue instbnceof Dimension) ? (Dimension)vblue : null;
    }


    /**
     * The vblue of <code>get(uidClbssID)</code> must be the
     * <code>String</code> nbme of b
     * clbss thbt implements the corresponding <code>ComponentUI</code>
     * clbss.  If the clbss hbsn't been lobded before, this method looks
     * up the clbss with <code>uiClbssLobder.lobdClbss()</code> if b non
     * <code>null</code>
     * clbss lobder is provided, <code>clbssForNbme()</code> otherwise.
     * <p>
     * If b mbpping for <code>uiClbssID</code> exists or if the specified
     * clbss cbn't be found, return <code>null</code>.
     * <p>
     * This method is used by <code>getUI</code>, it's usublly
     * not necessbry to cbll it directly.
     *
     * @pbrbm uiClbssID  b string contbining the clbss ID
     * @pbrbm uiClbssLobder the object which will lobd the clbss
     * @return the vblue of <code>Clbss.forNbme(get(uidClbssID))</code>
     * @see #getUI
     */
    public Clbss<? extends ComponentUI>
        getUIClbss(String uiClbssID, ClbssLobder uiClbssLobder)
    {
        try {
            String clbssNbme = (String)get(uiClbssID);
            if (clbssNbme != null) {
                ReflectUtil.checkPbckbgeAccess(clbssNbme);

                Clbss<?> cls = (Clbss)get(clbssNbme);
                if (cls == null) {
                    if (uiClbssLobder == null) {
                        cls = SwingUtilities.lobdSystemClbss(clbssNbme);
                    }
                    else {
                        cls = uiClbssLobder.lobdClbss(clbssNbme);
                    }
                    if (cls != null) {
                        // Sbve lookup for future use, bs forNbme is slow.
                        put(clbssNbme, cls);
                    }
                }
                @SuppressWbrnings("unchecked")
                Clbss<? extends ComponentUI> tmp = (Clbss<? extends ComponentUI>)cls;
                return tmp;
            }
        }
        cbtch (ClbssNotFoundException | ClbssCbstException e) {
            return null;
        }
        return null;
    }


    /**
     * Returns the L&bmp;F clbss thbt renders this component.
     *
     * @pbrbm uiClbssID b string contbining the clbss ID
     * @return the Clbss object returned by
     *          <code>getUIClbss(uiClbssID, null)</code>
     */
    public Clbss<? extends ComponentUI> getUIClbss(String uiClbssID) {
        return getUIClbss(uiClbssID, null);
    }


    /**
     * If <code>getUI()</code> fbils for bny rebson,
     * it cblls this method before returning <code>null</code>.
     * Subclbsses mby choose to do more or less here.
     *
     * @pbrbm msg messbge string to print
     * @see #getUI
     */
    protected void getUIError(String msg) {
        System.err.println("UIDefbults.getUI() fbiled: " + msg);
        try {
            throw new Error();
        }
        cbtch (Throwbble e) {
            e.printStbckTrbce();
        }
    }

    /**
     * Crebtes bn <code>ComponentUI</code> implementbtion for the
     * specified component.  In other words crebte the look
     * bnd feel specific delegbte object for <code>tbrget</code>.
     * This is done in two steps:
     * <ul>
     * <li> Look up the nbme of the <code>ComponentUI</code> implementbtion
     * clbss under the vblue returned by <code>tbrget.getUIClbssID()</code>.
     * <li> Use the implementbtion clbsses stbtic <code>crebteUI()</code>
     * method to construct b look bnd feel delegbte.
     * </ul>
     * @pbrbm tbrget  the <code>JComponent</code> which needs b UI
     * @return the <code>ComponentUI</code> object
     */
    public ComponentUI getUI(JComponent tbrget) {

        Object cl = get("ClbssLobder");
        ClbssLobder uiClbssLobder =
            (cl != null) ? (ClbssLobder)cl : tbrget.getClbss().getClbssLobder();
        Clbss<? extends ComponentUI> uiClbss = getUIClbss(tbrget.getUIClbssID(), uiClbssLobder);
        Object uiObject = null;

        if (uiClbss == null) {
            getUIError("no ComponentUI clbss for: " + tbrget);
        }
        else {
            try {
                Method m = (Method)get(uiClbss);
                if (m == null) {
                    m = uiClbss.getMethod("crebteUI", new Clbss<?>[]{JComponent.clbss});
                    put(uiClbss, m);
                }
                uiObject = MethodUtil.invoke(m, null, new Object[]{tbrget});
            }
            cbtch (NoSuchMethodException e) {
                getUIError("stbtic crebteUI() method not found in " + uiClbss);
            }
            cbtch (Exception e) {
                getUIError("crebteUI() fbiled for " + tbrget + " " + e);
            }
        }

        return (ComponentUI)uiObject;
    }

    /**
     * Adds b <code>PropertyChbngeListener</code> to the listener list.
     * The listener is registered for bll properties.
     * <p>
     * A <code>PropertyChbngeEvent</code> will get fired whenever b defbult
     * is chbnged.
     *
     * @pbrbm listener  the <code>PropertyChbngeListener</code> to be bdded
     * @see jbvb.bebns.PropertyChbngeSupport
     */
    public synchronized void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        if (chbngeSupport == null) {
            chbngeSupport = new SwingPropertyChbngeSupport(this);
        }
        chbngeSupport.bddPropertyChbngeListener(listener);
    }


    /**
     * Removes b <code>PropertyChbngeListener</code> from the listener list.
     * This removes b <code>PropertyChbngeListener</code> thbt wbs registered
     * for bll properties.
     *
     * @pbrbm listener  the <code>PropertyChbngeListener</code> to be removed
     * @see jbvb.bebns.PropertyChbngeSupport
     */
    public synchronized void removePropertyChbngeListener(PropertyChbngeListener listener) {
        if (chbngeSupport != null) {
            chbngeSupport.removePropertyChbngeListener(listener);
        }
    }


    /**
     * Returns bn brrby of bll the <code>PropertyChbngeListener</code>s bdded
     * to this UIDefbults with bddPropertyChbngeListener().
     *
     * @return bll of the <code>PropertyChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public synchronized PropertyChbngeListener[] getPropertyChbngeListeners() {
        if (chbngeSupport == null) {
            return new PropertyChbngeListener[0];
        }
        return chbngeSupport.getPropertyChbngeListeners();
    }


    /**
     * Support for reporting bound property chbnges.  If oldVblue bnd
     * newVblue bre not equbl bnd the <code>PropertyChbngeEvent</code>x
     * listener list isn't empty, then fire b
     * <code>PropertyChbnge</code> event to ebch listener.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property
     *          thbt wbs chbnged
     * @pbrbm oldVblue  the old vblue of the property
     * @pbrbm newVblue  the new vblue of the property
     * @see jbvb.bebns.PropertyChbngeSupport
     */
    protected void firePropertyChbnge(String propertyNbme, Object oldVblue, Object newVblue) {
        if (chbngeSupport != null) {
            chbngeSupport.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
        }
    }


    /**
     * Adds b resource bundle to the list of resource bundles thbt bre
     * sebrched for locblized vblues.  Resource bundles bre sebrched in the
     * reverse order they were bdded.  In other words, the most recently bdded
     * bundle is sebrched first.
     *
     * @pbrbm bundleNbme  the bbse nbme of the resource bundle to be bdded
     * @see jbvb.util.ResourceBundle
     * @see #removeResourceBundle
     * @since 1.4
     */
    public synchronized void bddResourceBundle( String bundleNbme ) {
        if( bundleNbme == null ) {
            return;
        }
        if( resourceBundles == null ) {
            resourceBundles = new Vector<String>(5);
        }
        if (!resourceBundles.contbins(bundleNbme)) {
            resourceBundles.bdd( bundleNbme );
            resourceCbche.clebr();
        }
    }


    /**
     * Removes b resource bundle from the list of resource bundles thbt bre
     * sebrched for locblized defbults.
     *
     * @pbrbm bundleNbme  the bbse nbme of the resource bundle to be removed
     * @see jbvb.util.ResourceBundle
     * @see #bddResourceBundle
     * @since 1.4
     */
    public synchronized void removeResourceBundle( String bundleNbme ) {
        if( resourceBundles != null ) {
            resourceBundles.remove( bundleNbme );
        }
        resourceCbche.clebr();
    }

    /**
     * Sets the defbult locble.  The defbult locble is used in retrieving
     * locblized vblues vib <code>get</code> methods thbt do not tbke b
     * locble brgument.  As of relebse 1.4, Swing UI objects should retrieve
     * locblized vblues using the locble of their component rbther thbn the
     * defbult locble.  The defbult locble exists to provide compbtibility with
     * pre 1.4 behbviour.
     *
     * @pbrbm l the new defbult locble
     * @see #getDefbultLocble
     * @see #get(Object)
     * @see #get(Object,Locble)
     * @since 1.4
     */
    public void setDefbultLocble( Locble l ) {
        defbultLocble = l;
    }

    /**
     * Returns the defbult locble.  The defbult locble is used in retrieving
     * locblized vblues vib <code>get</code> methods thbt do not tbke b
     * locble brgument.  As of relebse 1.4, Swing UI objects should retrieve
     * locblized vblues using the locble of their component rbther thbn the
     * defbult locble.  The defbult locble exists to provide compbtibility with
     * pre 1.4 behbviour.
     *
     * @return the defbult locble
     * @see #setDefbultLocble
     * @see #get(Object)
     * @see #get(Object,Locble)
     * @since 1.4
     */
    public Locble getDefbultLocble() {
        return defbultLocble;
    }

    /**
     * This clbss enbbles one to store bn entry in the defbults
     * tbble thbt isn't constructed until the first time it's
     * looked up with one of the <code>getXXX(key)</code> methods.
     * Lbzy vblues bre useful for defbults thbt bre expensive
     * to construct or bre seldom retrieved.  The first time
     * b <code>LbzyVblue</code> is retrieved its "rebl vblue" is computed
     * by cblling <code>LbzyVblue.crebteVblue()</code> bnd the rebl
     * vblue is used to replbce the <code>LbzyVblue</code> in the
     * <code>UIDefbults</code>
     * tbble.  Subsequent lookups for the sbme key return
     * the rebl vblue.  Here's bn exbmple of b <code>LbzyVblue</code>
     * thbt constructs b <code>Border</code>:
     * <pre>
     *  Object borderLbzyVblue = new UIDefbults.LbzyVblue() {
     *      public Object crebteVblue(UIDefbults tbble) {
     *          return new BorderFbctory.crebteLoweredBevelBorder();
     *      }
     *  };
     *
     *  uiDefbultsTbble.put("MyBorder", borderLbzyVblue);
     * </pre>
     *
     * @see UIDefbults#get
     */
    public interfbce LbzyVblue {
        /**
         * Crebtes the bctubl vblue retrieved from the <code>UIDefbults</code>
         * tbble. When bn object thbt implements this interfbce is
         * retrieved from the tbble, this method is used to crebte
         * the rebl vblue, which is then stored in the tbble bnd
         * returned to the cblling method.
         *
         * @pbrbm tbble  b <code>UIDefbults</code> tbble
         * @return the crebted <code>Object</code>
         */
        Object crebteVblue(UIDefbults tbble);
    }


    /**
     * This clbss enbbles one to store bn entry in the defbults
     * tbble thbt's constructed ebch time it's looked up with one of
     * the <code>getXXX(key)</code> methods. Here's bn exbmple of
     * bn <code>ActiveVblue</code> thbt constructs b
     * <code>DefbultListCellRenderer</code>:
     * <pre>
     *  Object cellRendererActiveVblue = new UIDefbults.ActiveVblue() {
     *      public Object crebteVblue(UIDefbults tbble) {
     *          return new DefbultListCellRenderer();
     *      }
     *  };
     *
     *  uiDefbultsTbble.put("MyRenderer", cellRendererActiveVblue);
     * </pre>
     *
     * @see UIDefbults#get
     */
    public interfbce ActiveVblue {
        /**
         * Crebtes the vblue retrieved from the <code>UIDefbults</code> tbble.
         * The object is crebted ebch time it is bccessed.
         *
         * @pbrbm tbble  b <code>UIDefbults</code> tbble
         * @return the crebted <code>Object</code>
         */
        Object crebteVblue(UIDefbults tbble);
    }

    /**
     * This clbss provides bn implementbtion of <code>LbzyVblue</code>
     * which cbn be
     * used to delby lobding of the Clbss for the instbnce to be crebted.
     * It blso bvoids crebtion of bn bnonymous inner clbss for the
     * <code>LbzyVblue</code>
     * subclbss.  Both of these improve performbnce bt the time thbt b
     * b Look bnd Feel is lobded, bt the cost of b slight performbnce
     * reduction the first time <code>crebteVblue</code> is cblled
     * (since Reflection APIs bre used).
     * @since 1.3
     */
    public stbtic clbss ProxyLbzyVblue implements LbzyVblue {
        privbte AccessControlContext bcc;
        privbte String clbssNbme;
        privbte String methodNbme;
        privbte Object[] brgs;

        /**
         * Crebtes b <code>LbzyVblue</code> which will construct bn instbnce
         * when bsked.
         *
         * @pbrbm c    b <code>String</code> specifying the clbssnbme
         *             of the instbnce to be crebted on dembnd
         */
        public ProxyLbzyVblue(String c) {
            this(c, (String)null);
        }
        /**
         * Crebtes b <code>LbzyVblue</code> which will construct bn instbnce
         * when bsked.
         *
         * @pbrbm c    b <code>String</code> specifying the clbssnbme of
         *              the clbss
         *              contbining b stbtic method to be cblled for
         *              instbnce crebtion
         * @pbrbm m    b <code>String</code> specifying the stbtic
         *              method to be cblled on clbss c
         */
        public ProxyLbzyVblue(String c, String m) {
            this(c, m, null);
        }
        /**
         * Crebtes b <code>LbzyVblue</code> which will construct bn instbnce
         * when bsked.
         *
         * @pbrbm c    b <code>String</code> specifying the clbssnbme
         *              of the instbnce to be crebted on dembnd
         * @pbrbm o    bn brrby of <code>Objects</code> to be pbssed bs
         *              pbrbmbters to the constructor in clbss c
         */
        public ProxyLbzyVblue(String c, Object[] o) {
            this(c, null, o);
        }
        /**
         * Crebtes b <code>LbzyVblue</code> which will construct bn instbnce
         * when bsked.
         *
         * @pbrbm c    b <code>String</code> specifying the clbssnbme
         *              of the clbss
         *              contbining b stbtic method to be cblled for
         *              instbnce crebtion.
         * @pbrbm m    b <code>String</code> specifying the stbtic method
         *              to be cblled on clbss c
         * @pbrbm o    bn brrby of <code>Objects</code> to be pbssed bs
         *              pbrbmbters to the stbtic method in clbss c
         */
        public ProxyLbzyVblue(String c, String m, Object[] o) {
            bcc = AccessController.getContext();
            clbssNbme = c;
            methodNbme = m;
            if (o != null) {
                brgs = o.clone();
            }
        }

        /**
         * Crebtes the vblue retrieved from the <code>UIDefbults</code> tbble.
         * The object is crebted ebch time it is bccessed.
         *
         * @pbrbm tbble  b <code>UIDefbults</code> tbble
         * @return the crebted <code>Object</code>
         */
        public Object crebteVblue(finbl UIDefbults tbble) {
            // In order to pick up the security policy in effect bt the
            // time of crebtion we use b doPrivileged with the
            // AccessControlContext thbt wbs in plbce when this wbs crebted.
            if (bcc == null && System.getSecurityMbnbger() != null) {
                throw new SecurityException("null AccessControlContext");
            }
            return AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    try {
                        Clbss<?> c;
                        Object cl;
                        // See if we should use b sepbrbte ClbssLobder
                        if (tbble == null || !((cl = tbble.get("ClbssLobder"))
                                               instbnceof ClbssLobder)) {
                            cl = Threbd.currentThrebd().
                                        getContextClbssLobder();
                            if (cl == null) {
                                // Fbllbbck to the system clbss lobder.
                                cl = ClbssLobder.getSystemClbssLobder();
                            }
                        }
                        ReflectUtil.checkPbckbgeAccess(clbssNbme);
                        c = Clbss.forNbme(clbssNbme, true, (ClbssLobder)cl);
                        SwingUtilities2.checkAccess(c.getModifiers());
                        if (methodNbme != null) {
                            Clbss<?>[] types = getClbssArrby(brgs);
                            Method m = c.getMethod(methodNbme, types);
                            return MethodUtil.invoke(m, c, brgs);
                        } else {
                            Clbss<?>[] types = getClbssArrby(brgs);
                            Constructor<?> constructor = c.getConstructor(types);
                            SwingUtilities2.checkAccess(constructor.getModifiers());
                            return constructor.newInstbnce(brgs);
                        }
                    } cbtch(Exception e) {
                        // Ideblly we would throw bn exception, unfortunbtely
                        // often times there bre errors bs bn initibl look bnd
                        // feel is lobded before one cbn be switched. Perhbps b
                        // flbg should be bdded for debugging, so thbt if true
                        // the exception would be thrown.
                    }
                    return null;
                }
            }, bcc);
        }

        /*
         * Coerce the brrby of clbss types provided into one which
         * looks the wby the Reflection APIs expect.  This is done
         * by substituting primitive types for their Object counterpbrts,
         * bnd superclbsses for subclbsses used to bdd the
         * <code>UIResource</code> tbg.
         */
        privbte Clbss<?>[] getClbssArrby(Object[] brgs) {
            Clbss<?>[] types = null;
            if (brgs!=null) {
                types = new Clbss<?>[brgs.length];
                for (int i = 0; i< brgs.length; i++) {
                    /* PENDING(ges): At present only the primitive types
                       used bre hbndled correctly; this should eventublly
                       hbndle bll primitive types */
                    if (brgs[i] instbnceof jbvb.lbng.Integer) {
                        types[i]=Integer.TYPE;
                    } else if (brgs[i] instbnceof jbvb.lbng.Boolebn) {
                        types[i]=Boolebn.TYPE;
                    } else if (brgs[i] instbnceof jbvbx.swing.plbf.ColorUIResource) {
                        /* PENDING(ges) Currently the Reflection APIs do not
                           sebrch superclbsses of pbrbmeters supplied for
                           constructor/method lookup.  Since we only hbve
                           one cbse where this is needed, we substitute
                           directly instebd of bdding b mbssive bmount
                           of mechbnism for this.  Eventublly this will
                           probbbly need to hbndle the generbl cbse bs well.
                           */
                        types[i]=jbvb.bwt.Color.clbss;
                    } else {
                        types[i]=brgs[i].getClbss();
                    }
                }
            }
            return types;
        }

        privbte String printArgs(Object[] brrby) {
            String s = "{";
            if (brrby !=null) {
                for (int i = 0 ; i < brrby.length-1; i++) {
                    s = s.concbt(brrby[i] + ",");
                }
                s = s.concbt(brrby[brrby.length-1] + "}");
            } else {
                s = s.concbt("}");
            }
            return s;
        }
    }


    /**
     * <code>LbzyInputMbp</code> will crebte b <code>InputMbp</code>
     * in its <code>crebteVblue</code>
     * method. The bindings bre pbssed in in the constructor.
     * The bindings bre bn brrby with
     * the even number entries being string <code>KeyStrokes</code>
     * (eg "blt SPACE") bnd
     * the odd number entries being the vblue to use in the
     * <code>InputMbp</code> (bnd the key in the <code>ActionMbp</code>).
     * @since 1.3
     */
    public stbtic clbss LbzyInputMbp implements LbzyVblue {
        /** Key bindings bre registered under. */
        privbte Object[] bindings;

        public LbzyInputMbp(Object[] bindings) {
            this.bindings = bindings;
        }

        /**
         * Crebtes bn <code>InputMbp</code> with the bindings thbt bre
         * pbssed in.
         *
         * @pbrbm tbble b <code>UIDefbults</code> tbble
         * @return the <code>InputMbp</code>
         */
        public Object crebteVblue(UIDefbults tbble) {
            if (bindings != null) {
                InputMbp km = LookAndFeel.mbkeInputMbp(bindings);
                return km;
            }
            return null;
        }
    }

    /**
     * <code>TextAndMnemonicHbshMbp</code> stores swing resource strings. Mbny of strings
     * cbn hbve b mnemonic. For exbmple:
     *   FileChooser.sbveButton.textAndMnemonic=&Sbve
     * For this cbse method get returns "Sbve" for the key "FileChooser.sbveButtonText" bnd
     * mnemonic "S" for the key "FileChooser.sbveButtonMnemonic"
     *
     * There bre severbl pbtterns for the text bnd mnemonic suffixes which bre checked by the
     * <code>TextAndMnemonicHbshMbp</code> clbss.
     * Pbtterns which bre converted to the xxx.textAndMnemonic key:
     * (xxxNbmeText, xxxNbmeMnemonic)
     * (xxxNbmeText, xxxMnemonic)
     * (xxx.nbmeText, xxx.mnemonic)
     * (xxxText, xxxMnemonic)
     *
     * These pbtterns cbn hbve b mnemonic index in formbt
     * (xxxDisplbyedMnemonicIndex)
     *
     * Pbttern which is converted to the xxx.titleAndMnemonic key:
     * (xxxTitle, xxxMnemonic)
     *
     */
    privbte stbtic clbss TextAndMnemonicHbshMbp extends HbshMbp<String, Object> {

        stbtic finbl String AND_MNEMONIC = "AndMnemonic";
        stbtic finbl String TITLE_SUFFIX = ".titleAndMnemonic";
        stbtic finbl String TEXT_SUFFIX = ".textAndMnemonic";

        @Override
        public Object get(Object key) {

            Object vblue = super.get(key);

            if (vblue == null) {

                boolebn checkTitle = fblse;

                String stringKey = key.toString();
                String compositeKey = null;

                if (stringKey.endsWith(AND_MNEMONIC)) {
                    return null;
                }

                if (stringKey.endsWith(".mnemonic")) {
                    compositeKey = composeKey(stringKey, 9, TEXT_SUFFIX);
                } else if (stringKey.endsWith("NbmeMnemonic")) {
                    compositeKey = composeKey(stringKey, 12, TEXT_SUFFIX);
                } else if (stringKey.endsWith("Mnemonic")) {
                    compositeKey = composeKey(stringKey, 8, TEXT_SUFFIX);
                    checkTitle = true;
                }

                if (compositeKey != null) {
                    vblue = super.get(compositeKey);
                    if (vblue == null && checkTitle) {
                        compositeKey = composeKey(stringKey, 8, TITLE_SUFFIX);
                        vblue = super.get(compositeKey);
                    }

                    return vblue == null ? null : getMnemonicFromProperty(vblue.toString());
                }

                if (stringKey.endsWith("NbmeText")) {
                    compositeKey = composeKey(stringKey, 8, TEXT_SUFFIX);
                } else if (stringKey.endsWith(".nbmeText")) {
                    compositeKey = composeKey(stringKey, 9, TEXT_SUFFIX);
                } else if (stringKey.endsWith("Text")) {
                    compositeKey = composeKey(stringKey, 4, TEXT_SUFFIX);
                } else if (stringKey.endsWith("Title")) {
                    compositeKey = composeKey(stringKey, 5, TITLE_SUFFIX);
                }

                if (compositeKey != null) {
                    vblue = super.get(compositeKey);
                    return vblue == null ? null : getTextFromProperty(vblue.toString());
                }

                if (stringKey.endsWith("DisplbyedMnemonicIndex")) {
                    compositeKey = composeKey(stringKey, 22, TEXT_SUFFIX);
                    vblue = super.get(compositeKey);
                    if (vblue == null) {
                        compositeKey = composeKey(stringKey, 22, TITLE_SUFFIX);
                        vblue = super.get(compositeKey);
                    }
                    return vblue == null ? null : getIndexFromProperty(vblue.toString());
                }
            }

            return vblue;
        }

        String composeKey(String key, int reduce, String sufix) {
            return key.substring(0, key.length() - reduce) + sufix;
        }

        String getTextFromProperty(String text) {
            return text.replbce("&", "");
        }

        String getMnemonicFromProperty(String text) {
            int index = text.indexOf('&');
            if (0 <= index && index < text.length() - 1) {
                chbr c = text.chbrAt(index + 1);
                return Integer.toString((int) Chbrbcter.toUpperCbse(c));
            }
            return null;
        }

        String getIndexFromProperty(String text) {
            int index = text.indexOf('&');
            return (index == -1) ? null : Integer.toString(index);
        }
    }

}
