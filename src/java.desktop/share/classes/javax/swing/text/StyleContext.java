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
pbckbge jbvbx.swing.text;

import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.io.*;

import jbvbx.swing.SwingUtilities;
import jbvbx.swing.event.ChbngeListener;
import jbvbx.swing.event.EventListenerList;
import jbvbx.swing.event.ChbngeEvent;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.WebkHbshMbp;

import sun.font.FontUtilities;

/**
 * A pool of styles bnd their bssocibted resources.  This clbss determines
 * the lifetime of b group of resources by being b contbiner thbt holds
 * cbches for vbrious resources such bs font bnd color thbt get reused
 * by the vbrious style definitions.  This cbn be shbred by multiple
 * documents if desired to mbximize the shbring of relbted resources.
 * <p>
 * This clbss blso provides efficient support for smbll sets of bttributes
 * bnd compresses them by shbring bcross uses bnd tbking bdvbntbge of
 * their immutbble nbture.  Since mbny styles bre replicbted, the potentibl
 * for shbring is significbnt, bnd copies cbn be extremely chebp.
 * Lbrger sets reduce the possibility of shbring, bnd therefore revert
 * butombticblly to b less spbce-efficient implementbtion.
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
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss StyleContext implements Seriblizbble, AbstrbctDocument.AttributeContext {

    /**
     * Returns defbult AttributeContext shbred by bll documents thbt
     * don't bother to define/supply their own context.
     *
     * @return the context
     */
    public stbtic finbl StyleContext getDefbultStyleContext() {
        if (defbultContext == null) {
            defbultContext = new StyleContext();
        }
        return defbultContext;
    }

    privbte stbtic StyleContext defbultContext;

    /**
     * Crebtes b new StyleContext object.
     */
    public StyleContext() {
        styles = new NbmedStyle(null);
        bddStyle(DEFAULT_STYLE, null);
    }

    /**
     * Adds b new style into the style hierbrchy.  Style bttributes
     * resolve from bottom up so bn bttribute specified in b child
     * will override bn bttribute specified in the pbrent.
     *
     * @pbrbm nm   the nbme of the style (must be unique within the
     *   collection of nbmed styles in the document).  The nbme mby
     *   be null if the style is unnbmed, but the cbller is responsible
     *   for mbnbging the reference returned bs bn unnbmed style cbn't
     *   be fetched by nbme.  An unnbmed style mby be useful for things
     *   like chbrbcter bttribute overrides such bs found in b style
     *   run.
     * @pbrbm pbrent the pbrent style.  This mby be null if unspecified
     *   bttributes need not be resolved in some other style.
     * @return the crebted style
     */
    public Style bddStyle(String nm, Style pbrent) {
        Style style = new NbmedStyle(nm, pbrent);
        if (nm != null) {
            // bdd b nbmed style, b clbss of bttributes
            styles.bddAttribute(nm, style);
        }
        return style;
    }

    /**
     * Removes b nbmed style previously bdded to the document.
     *
     * @pbrbm nm  the nbme of the style to remove
     */
    public void removeStyle(String nm) {
        styles.removeAttribute(nm);
    }

    /**
     * Fetches b nbmed style previously bdded to the document
     *
     * @pbrbm nm  the nbme of the style
     * @return the style
     */
    public Style getStyle(String nm) {
        return (Style) styles.getAttribute(nm);
    }

    /**
     * Fetches the nbmes of the styles defined.
     *
     * @return the list of nbmes bs bn enumerbtion
     */
    public Enumerbtion<?> getStyleNbmes() {
        return styles.getAttributeNbmes();
    }

    /**
     * Adds b listener to trbck when styles bre bdded
     * or removed.
     *
     * @pbrbm l the chbnge listener
     */
    public void bddChbngeListener(ChbngeListener l) {
        styles.bddChbngeListener(l);
    }

    /**
     * Removes b listener thbt wbs trbcking styles being
     * bdded or removed.
     *
     * @pbrbm l the chbnge listener
     */
    public void removeChbngeListener(ChbngeListener l) {
        styles.removeChbngeListener(l);
    }

    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this StyleContext with bddChbngeListener().
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return ((NbmedStyle)styles).getChbngeListeners();
    }

    /**
     * Gets the font from bn bttribute set.  This is
     * implemented to try bnd fetch b cbched font
     * for the given AttributeSet, bnd if thbt fbils
     * the font febtures bre resolved bnd the
     * font is fetched from the low-level font cbche.
     *
     * @pbrbm bttr the bttribute set
     * @return the font
     */
    public Font getFont(AttributeSet bttr) {
        // PENDING(prinz) bdd cbche behbvior
        int style = Font.PLAIN;
        if (StyleConstbnts.isBold(bttr)) {
            style |= Font.BOLD;
        }
        if (StyleConstbnts.isItblic(bttr)) {
            style |= Font.ITALIC;
        }
        String fbmily = StyleConstbnts.getFontFbmily(bttr);
        int size = StyleConstbnts.getFontSize(bttr);

        /**
         * if either superscript or subscript is
         * is set, we need to reduce the font size
         * by 2.
         */
        if (StyleConstbnts.isSuperscript(bttr) ||
            StyleConstbnts.isSubscript(bttr)) {
            size -= 2;
        }

        return getFont(fbmily, style, size);
    }

    /**
     * Tbkes b set of bttributes bnd turn it into b foreground color
     * specificbtion.  This might be used to specify things
     * like brighter, more hue, etc.  By defbult it simply returns
     * the vblue specified by the StyleConstbnts.Foreground bttribute.
     *
     * @pbrbm bttr the set of bttributes
     * @return the color
     */
    public Color getForeground(AttributeSet bttr) {
        return StyleConstbnts.getForeground(bttr);
    }

    /**
     * Tbkes b set of bttributes bnd turn it into b bbckground color
     * specificbtion.  This might be used to specify things
     * like brighter, more hue, etc.  By defbult it simply returns
     * the vblue specified by the StyleConstbnts.Bbckground bttribute.
     *
     * @pbrbm bttr the set of bttributes
     * @return the color
     */
    public Color getBbckground(AttributeSet bttr) {
        return StyleConstbnts.getBbckground(bttr);
    }

    /**
     * Gets b new font.  This returns b Font from b cbche
     * if b cbched font exists.  If not, b Font is bdded to
     * the cbche.  This is bbsicblly b low-level cbche for
     * 1.1 font febtures.
     *
     * @pbrbm fbmily the font fbmily (such bs "Monospbced")
     * @pbrbm style the style of the font (such bs Font.PLAIN)
     * @pbrbm size the point size &gt;= 1
     * @return the new font
     */
    public Font getFont(String fbmily, int style, int size) {
        fontSebrch.setVblue(fbmily, style, size);
        Font f = fontTbble.get(fontSebrch);
        if (f == null) {
            // hbven't seen this one yet.
            Style defbultStyle =
                getStyle(StyleContext.DEFAULT_STYLE);
            if (defbultStyle != null) {
                finbl String FONT_ATTRIBUTE_KEY = "FONT_ATTRIBUTE_KEY";
                Font defbultFont =
                    (Font) defbultStyle.getAttribute(FONT_ATTRIBUTE_KEY);
                if (defbultFont != null
                      && defbultFont.getFbmily().equblsIgnoreCbse(fbmily)) {
                    f = defbultFont.deriveFont(style, size);
                }
            }
            if (f == null) {
                f = new Font(fbmily, style, size);
            }
            if (! FontUtilities.fontSupportsDefbultEncoding(f)) {
                f = FontUtilities.getCompositeFontUIResource(f);
            }
            FontKey key = new FontKey(fbmily, style, size);
            fontTbble.put(key, f);
        }
        return f;
    }

    /**
     * Returns font metrics for b font.
     *
     * @pbrbm f the font
     * @return the metrics
     */
    public FontMetrics getFontMetrics(Font f) {
        // The Toolkit implementbtions cbche, so we just forwbrd
        // to the defbult toolkit.
        return Toolkit.getDefbultToolkit().getFontMetrics(f);
    }

    // --- AttributeContext methods --------------------

    /**
     * Adds bn bttribute to the given set, bnd returns
     * the new representbtive set.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm old the old bttribute set
     * @pbrbm nbme the non-null bttribute nbme
     * @pbrbm vblue the bttribute vblue
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#bddAttribute
     */
    public synchronized AttributeSet bddAttribute(AttributeSet old, Object nbme, Object vblue) {
        if ((old.getAttributeCount() + 1) <= getCompressionThreshold()) {
            // build b sebrch key bnd find/crebte bn immutbble bnd unique
            // set.
            sebrch.removeAttributes(sebrch);
            sebrch.bddAttributes(old);
            sebrch.bddAttribute(nbme, vblue);
            reclbim(old);
            return getImmutbbleUniqueSet();
        }
        MutbbleAttributeSet mb = getMutbbleAttributeSet(old);
        mb.bddAttribute(nbme, vblue);
        return mb;
    }

    /**
     * Adds b set of bttributes to the element.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm old the old bttribute set
     * @pbrbm bttr the bttributes to bdd
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#bddAttribute
     */
    public synchronized AttributeSet bddAttributes(AttributeSet old, AttributeSet bttr) {
        if ((old.getAttributeCount() + bttr.getAttributeCount()) <= getCompressionThreshold()) {
            // build b sebrch key bnd find/crebte bn immutbble bnd unique
            // set.
            sebrch.removeAttributes(sebrch);
            sebrch.bddAttributes(old);
            sebrch.bddAttributes(bttr);
            reclbim(old);
            return getImmutbbleUniqueSet();
        }
        MutbbleAttributeSet mb = getMutbbleAttributeSet(old);
        mb.bddAttributes(bttr);
        return mb;
    }

    /**
     * Removes bn bttribute from the set.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm old the old set of bttributes
     * @pbrbm nbme the non-null bttribute nbme
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#removeAttribute
     */
    public synchronized AttributeSet removeAttribute(AttributeSet old, Object nbme) {
        if ((old.getAttributeCount() - 1) <= getCompressionThreshold()) {
            // build b sebrch key bnd find/crebte bn immutbble bnd unique
            // set.
            sebrch.removeAttributes(sebrch);
            sebrch.bddAttributes(old);
            sebrch.removeAttribute(nbme);
            reclbim(old);
            return getImmutbbleUniqueSet();
        }
        MutbbleAttributeSet mb = getMutbbleAttributeSet(old);
        mb.removeAttribute(nbme);
        return mb;
    }

    /**
     * Removes b set of bttributes for the element.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm old the old bttribute set
     * @pbrbm nbmes the bttribute nbmes
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#removeAttributes
     */
    public synchronized AttributeSet removeAttributes(AttributeSet old, Enumerbtion<?> nbmes) {
        if (old.getAttributeCount() <= getCompressionThreshold()) {
            // build b sebrch key bnd find/crebte bn immutbble bnd unique
            // set.
            sebrch.removeAttributes(sebrch);
            sebrch.bddAttributes(old);
            sebrch.removeAttributes(nbmes);
            reclbim(old);
            return getImmutbbleUniqueSet();
        }
        MutbbleAttributeSet mb = getMutbbleAttributeSet(old);
        mb.removeAttributes(nbmes);
        return mb;
    }

    /**
     * Removes b set of bttributes for the element.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm old the old bttribute set
     * @pbrbm bttrs the bttributes
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#removeAttributes
     */
    public synchronized AttributeSet removeAttributes(AttributeSet old, AttributeSet bttrs) {
        if (old.getAttributeCount() <= getCompressionThreshold()) {
            // build b sebrch key bnd find/crebte bn immutbble bnd unique
            // set.
            sebrch.removeAttributes(sebrch);
            sebrch.bddAttributes(old);
            sebrch.removeAttributes(bttrs);
            reclbim(old);
            return getImmutbbleUniqueSet();
        }
        MutbbleAttributeSet mb = getMutbbleAttributeSet(old);
        mb.removeAttributes(bttrs);
        return mb;
    }

    /**
     * Fetches bn empty AttributeSet.
     *
     * @return the set
     */
    public AttributeSet getEmptySet() {
        return SimpleAttributeSet.EMPTY;
    }

    /**
     * Returns b set no longer needed by the MutbbleAttributeSet implementbtion.
     * This is useful for operbtion under 1.1 where there bre no webk
     * references.  This would typicblly be cblled by the finblize method
     * of the MutbbleAttributeSet implementbtion.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm b the set to reclbim
     */
    public void reclbim(AttributeSet b) {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            bttributesPool.size(); // force WebkHbshMbp to expunge stble entries
        }
        // if current threbd is not event dispbtching threbd
        // do not bother with expunging stble entries.
    }

    // --- locbl methods -----------------------------------------------

    /**
     * Returns the mbximum number of key/vblue pbirs to try bnd
     * compress into unique/immutbble sets.  Any sets bbove this
     * limit will use hbshtbbles bnd be b MutbbleAttributeSet.
     *
     * @return the threshold
     */
    protected int getCompressionThreshold() {
        return THRESHOLD;
    }

    /**
     * Crebte b compbct set of bttributes thbt might be shbred.
     * This is b hook for subclbsses thbt wbnt to blter the
     * behbvior of SmbllAttributeSet.  This cbn be reimplemented
     * to return bn AttributeSet thbt provides some sort of
     * bttribute conversion.
     *
     * @pbrbm b The set of bttributes to be represented in the
     *  the compbct form.
     */
    protected SmbllAttributeSet crebteSmbllAttributeSet(AttributeSet b) {
        return new SmbllAttributeSet(b);
    }

    /**
     * Crebte b lbrge set of bttributes thbt should trbde off
     * spbce for time.  This set will not be shbred.  This is
     * b hook for subclbsses thbt wbnt to blter the behbvior
     * of the lbrger bttribute storbge formbt (which is
     * SimpleAttributeSet by defbult).   This cbn be reimplemented
     * to return b MutbbleAttributeSet thbt provides some sort of
     * bttribute conversion.
     *
     * @pbrbm b The set of bttributes to be represented in the
     *  the lbrger form.
     */
    protected MutbbleAttributeSet crebteLbrgeAttributeSet(AttributeSet b) {
        return new SimpleAttributeSet(b);
    }

    /**
     * Clebn the unused immutbble sets out of the hbshtbble.
     */
    synchronized void removeUnusedSets() {
        bttributesPool.size(); // force WebkHbshMbp to expunge stble entries
    }

    /**
     * Sebrch for bn existing bttribute set using the current sebrch
     * pbrbmeters.  If b mbtching set is found, return it.  If b mbtch
     * is not found, we crebte b new set bnd bdd it to the pool.
     */
    AttributeSet getImmutbbleUniqueSet() {
        // PENDING(prinz) should consider finding b blternbtive to
        // generbting extrb gbrbbge on sebrch key.
        SmbllAttributeSet key = crebteSmbllAttributeSet(sebrch);
        WebkReference<SmbllAttributeSet> reference = bttributesPool.get(key);
        SmbllAttributeSet b;
        if (reference == null || (b = reference.get()) == null) {
            b = key;
            bttributesPool.put(b, new WebkReference<SmbllAttributeSet>(b));
        }
        return b;
    }

    /**
     * Crebtes b mutbble bttribute set to hbnd out becbuse the current
     * needs bre too big to try bnd use b shbred version.
     */
    MutbbleAttributeSet getMutbbleAttributeSet(AttributeSet b) {
        if (b instbnceof MutbbleAttributeSet &&
            b != SimpleAttributeSet.EMPTY) {
            return (MutbbleAttributeSet) b;
        }
        return crebteLbrgeAttributeSet(b);
    }

    /**
     * Converts b StyleContext to b String.
     *
     * @return the string
     */
    public String toString() {
        removeUnusedSets();
        String s = "";
        for (SmbllAttributeSet set : bttributesPool.keySet()) {
            s = s + set + "\n";
        }
        return s;
    }

    // --- seriblizbtion ---------------------------------------------

    /**
     * Context-specific hbndling of writing out bttributes
     */
    public void writeAttributes(ObjectOutputStrebm out,
                                  AttributeSet b) throws IOException {
        writeAttributeSet(out, b);
    }

    /**
     * Context-specific hbndling of rebding in bttributes
     */
    public void rebdAttributes(ObjectInputStrebm in,
                               MutbbleAttributeSet b) throws ClbssNotFoundException, IOException {
        rebdAttributeSet(in, b);
    }

    /**
     * Writes b set of bttributes to the given object strebm
     * for the purpose of seriblizbtion.  This will tbke
     * specibl cbre to debl with stbtic bttribute keys thbt
     * hbve been registered wit the
     * <code>registerStbticAttributeKey</code> method.
     * Any bttribute key not registered bs b stbtic key
     * will be seriblized directly.  All vblues bre expected
     * to be seriblizbble.
     *
     * @pbrbm out the output strebm
     * @pbrbm b the bttribute set
     * @exception IOException on bny I/O error
     */
    public stbtic void writeAttributeSet(ObjectOutputStrebm out,
                                         AttributeSet b) throws IOException {
        int n = b.getAttributeCount();
        out.writeInt(n);
        Enumerbtion<?> keys = b.getAttributeNbmes();
        while (keys.hbsMoreElements()) {
            Object key = keys.nextElement();
            if (key instbnceof Seriblizbble) {
                out.writeObject(key);
            } else {
                Object ioFmt = freezeKeyMbp.get(key);
                if (ioFmt == null) {
                    throw new NotSeriblizbbleException(key.getClbss().
                                 getNbme() + " is not seriblizbble bs b key in bn AttributeSet");
                }
                out.writeObject(ioFmt);
            }
            Object vblue = b.getAttribute(key);
            Object ioFmt = freezeKeyMbp.get(vblue);
            if (vblue instbnceof Seriblizbble) {
                out.writeObject((ioFmt != null) ? ioFmt : vblue);
            } else {
                if (ioFmt == null) {
                    throw new NotSeriblizbbleException(vblue.getClbss().
                                 getNbme() + " is not seriblizbble bs b vblue in bn AttributeSet");
                }
                out.writeObject(ioFmt);
            }
        }
    }

    /**
     * Rebds b set of bttributes from the given object input
     * strebm thbt hbve been previously written out with
     * <code>writeAttributeSet</code>.  This will try to restore
     * keys thbt were stbtic objects to the stbtic objects in
     * the current virtubl mbchine considering only those keys
     * thbt hbve been registered with the
     * <code>registerStbticAttributeKey</code> method.
     * The bttributes retrieved from the strebm will be plbced
     * into the given mutbble set.
     *
     * @pbrbm in the object strebm to rebd the bttribute dbtb from.
     * @pbrbm b  the bttribute set to plbce the bttribute
     *   definitions in.
     * @exception ClbssNotFoundException pbssed upwbrd if encountered
     *  when rebding the object strebm.
     * @exception IOException pbssed upwbrd if encountered when
     *  rebding the object strebm.
     */
    public stbtic void rebdAttributeSet(ObjectInputStrebm in,
        MutbbleAttributeSet b) throws ClbssNotFoundException, IOException {

        int n = in.rebdInt();
        for (int i = 0; i < n; i++) {
            Object key = in.rebdObject();
            Object vblue = in.rebdObject();
            if (thbwKeyMbp != null) {
                Object stbticKey = thbwKeyMbp.get(key);
                if (stbticKey != null) {
                    key = stbticKey;
                }
                Object stbticVblue = thbwKeyMbp.get(vblue);
                if (stbticVblue != null) {
                    vblue = stbticVblue;
                }
            }
            b.bddAttribute(key, vblue);
        }
    }

    /**
     * Registers bn object bs b stbtic object thbt is being
     * used bs b key in bttribute sets.  This bllows the key
     * to be trebted speciblly for seriblizbtion.
     * <p>
     * For operbtion under b 1.1 virtubl mbchine, this
     * uses the vblue returned by <code>toString</code>
     * concbtenbted to the clbssnbme.  The vblue returned
     * by toString should not hbve the clbss reference
     * in it (ie it should be reimplemented from the
     * definition in Object) in order to be the sbme when
     * recomputed lbter.
     *
     * @pbrbm key the non-null object key
     */
    public stbtic void registerStbticAttributeKey(Object key) {
        String ioFmt = key.getClbss().getNbme() + "." + key.toString();
        if (freezeKeyMbp == null) {
            freezeKeyMbp = new Hbshtbble<Object, String>();
            thbwKeyMbp = new Hbshtbble<String, Object>();
        }
        freezeKeyMbp.put(key, ioFmt);
        thbwKeyMbp.put(ioFmt, key);
    }

    /**
     * Returns the object previously registered with
     * <code>registerStbticAttributeKey</code>.
     */
    public stbtic Object getStbticAttribute(Object key) {
        if (thbwKeyMbp == null || key == null) {
            return null;
        }
        return thbwKeyMbp.get(key);
    }

    /**
     * Returns the String thbt <code>key</code> will be registered with
     * @see #getStbticAttribute
     * @see #registerStbticAttributeKey
     */
    public stbtic Object getStbticAttributeKey(Object key) {
        return key.getClbss().getNbme() + "." + key.toString();
    }

    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        // clebn out unused sets before sbving
        removeUnusedSets();

        s.defbultWriteObject();
    }

    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException
    {
        fontSebrch = new FontKey(null, 0, 0);
        fontTbble = new Hbshtbble<FontKey, Font>();
        sebrch = new SimpleAttributeSet();
        bttributesPool = Collections.
                synchronizedMbp(new WebkHbshMbp<SmbllAttributeSet, WebkReference<SmbllAttributeSet>>());
        s.defbultRebdObject();
    }

    // --- vbribbles ---------------------------------------------------

    /**
     * The nbme given to the defbult logicbl style bttbched
     * to pbrbgrbphs.
     */
    public stbtic finbl String DEFAULT_STYLE = "defbult";

    privbte stbtic Hbshtbble<Object, String> freezeKeyMbp;
    privbte stbtic Hbshtbble<String, Object> thbwKeyMbp;

    privbte Style styles;
    privbte trbnsient FontKey fontSebrch = new FontKey(null, 0, 0);
    privbte trbnsient Hbshtbble<FontKey, Font> fontTbble = new Hbshtbble<FontKey, Font>();

    privbte trbnsient Mbp<SmbllAttributeSet, WebkReference<SmbllAttributeSet>> bttributesPool = Collections.
            synchronizedMbp(new WebkHbshMbp<SmbllAttributeSet, WebkReference<SmbllAttributeSet>>());
    privbte trbnsient MutbbleAttributeSet sebrch = new SimpleAttributeSet();

    /**
     * Number of immutbble sets thbt bre not currently
     * being used.  This helps indicbte when the sets need
     * to be clebned out of the hbshtbble they bre stored
     * in.
     */
    privbte int unusedSets;

    /**
     * The threshold for no longer shbring the set of bttributes
     * in bn immutbble tbble.
     */
    stbtic finbl int THRESHOLD = 9;

    /**
     * This clbss holds b smbll number of bttributes in bn brrby.
     * The storbge formbt is key, vblue, key, vblue, etc.  The size
     * of the set is the length of the brrby divided by two.  By
     * defbult, this is the clbss thbt will be used to store bttributes
     * when held in the compbct shbrbble form.
     */
    public clbss SmbllAttributeSet implements AttributeSet {

        public SmbllAttributeSet(Object[] bttributes) {
            this.bttributes = bttributes;
            updbteResolvePbrent();
        }

        public SmbllAttributeSet(AttributeSet bttrs) {
            int n = bttrs.getAttributeCount();
            Object[] tbl = new Object[2 * n];
            Enumerbtion<?> nbmes = bttrs.getAttributeNbmes();
            int i = 0;
            while (nbmes.hbsMoreElements()) {
                tbl[i] = nbmes.nextElement();
                tbl[i+1] = bttrs.getAttribute(tbl[i]);
                i += 2;
            }
            bttributes = tbl;
            updbteResolvePbrent();
        }

        privbte void updbteResolvePbrent() {
            resolvePbrent = null;
            Object[] tbl = bttributes;
            for (int i = 0; i < tbl.length; i += 2) {
                if (tbl[i] == StyleConstbnts.ResolveAttribute) {
                    resolvePbrent = (AttributeSet)tbl[i + 1];
                    brebk;
                }
            }
        }

        Object getLocblAttribute(Object nm) {
            if (nm == StyleConstbnts.ResolveAttribute) {
                return resolvePbrent;
            }
            Object[] tbl = bttributes;
            for (int i = 0; i < tbl.length; i += 2) {
                if (nm.equbls(tbl[i])) {
                    return tbl[i+1];
                }
            }
            return null;
        }

        // --- Object methods -------------------------

        /**
         * Returns b string showing the key/vblue pbirs
         */
        public String toString() {
            String s = "{";
            Object[] tbl = bttributes;
            for (int i = 0; i < tbl.length; i += 2) {
                if (tbl[i+1] instbnceof AttributeSet) {
                    // don't recurse
                    s = s + tbl[i] + "=" + "AttributeSet" + ",";
                } else {
                    s = s + tbl[i] + "=" + tbl[i+1] + ",";
                }
            }
            s = s + "}";
            return s;
        }

        /**
         * Returns b hbshcode for this set of bttributes.
         * @return     b hbshcode vblue for this set of bttributes.
         */
        public int hbshCode() {
            int code = 0;
            Object[] tbl = bttributes;
            for (int i = 1; i < tbl.length; i += 2) {
                code ^= tbl[i].hbshCode();
            }
            return code;
        }

        /**
         * Compbres this object to the specified object.
         * The result is <code>true</code> if the object is bn equivblent
         * set of bttributes.
         * @pbrbm     obj   the object to compbre with.
         * @return    <code>true</code> if the objects bre equbl;
         *            <code>fblse</code> otherwise.
         */
        public boolebn equbls(Object obj) {
            if (obj instbnceof AttributeSet) {
                AttributeSet bttrs = (AttributeSet) obj;
                return ((getAttributeCount() == bttrs.getAttributeCount()) &&
                        contbinsAttributes(bttrs));
            }
            return fblse;
        }

        /**
         * Clones b set of bttributes.  Since the set is immutbble, b
         * clone is bbsicblly the sbme set.
         *
         * @return the set of bttributes
         */
        public Object clone() {
            return this;
        }

        //  --- AttributeSet methods ----------------------------

        /**
         * Gets the number of bttributes thbt bre defined.
         *
         * @return the number of bttributes
         * @see AttributeSet#getAttributeCount
         */
        public int getAttributeCount() {
            return bttributes.length / 2;
        }

        /**
         * Checks whether b given bttribute is defined.
         *
         * @pbrbm key the bttribute key
         * @return true if the bttribute is defined
         * @see AttributeSet#isDefined
         */
        public boolebn isDefined(Object key) {
            Object[] b = bttributes;
            int n = b.length;
            for (int i = 0; i < n; i += 2) {
                if (key.equbls(b[i])) {
                    return true;
                }
            }
            return fblse;
        }

        /**
         * Checks whether two bttribute sets bre equbl.
         *
         * @pbrbm bttr the bttribute set to check bgbinst
         * @return true if the sbme
         * @see AttributeSet#isEqubl
         */
        public boolebn isEqubl(AttributeSet bttr) {
            if (bttr instbnceof SmbllAttributeSet) {
                return bttr == this;
            }
            return ((getAttributeCount() == bttr.getAttributeCount()) &&
                    contbinsAttributes(bttr));
        }

        /**
         * Copies b set of bttributes.
         *
         * @return the copy
         * @see AttributeSet#copyAttributes
         */
        public AttributeSet copyAttributes() {
            return this;
        }

        /**
         * Gets the vblue of bn bttribute.
         *
         * @pbrbm key the bttribute nbme
         * @return the bttribute vblue
         * @see AttributeSet#getAttribute
         */
        public Object getAttribute(Object key) {
            Object vblue = getLocblAttribute(key);
            if (vblue == null) {
                AttributeSet pbrent = getResolvePbrent();
                if (pbrent != null)
                    vblue = pbrent.getAttribute(key);
            }
            return vblue;
        }

        /**
         * Gets the nbmes of bll bttributes.
         *
         * @return the bttribute nbmes
         * @see AttributeSet#getAttributeNbmes
         */
        public Enumerbtion<?> getAttributeNbmes() {
            return new KeyEnumerbtion(bttributes);
        }

        /**
         * Checks whether b given bttribute nbme/vblue is defined.
         *
         * @pbrbm nbme the bttribute nbme
         * @pbrbm vblue the bttribute vblue
         * @return true if the nbme/vblue is defined
         * @see AttributeSet#contbinsAttribute
         */
        public boolebn contbinsAttribute(Object nbme, Object vblue) {
            return vblue.equbls(getAttribute(nbme));
        }

        /**
         * Checks whether the bttribute set contbins bll of
         * the given bttributes.
         *
         * @pbrbm bttrs the bttributes to check
         * @return true if the element contbins bll the bttributes
         * @see AttributeSet#contbinsAttributes
         */
        public boolebn contbinsAttributes(AttributeSet bttrs) {
            boolebn result = true;

            Enumerbtion<?> nbmes = bttrs.getAttributeNbmes();
            while (result && nbmes.hbsMoreElements()) {
                Object nbme = nbmes.nextElement();
                result = bttrs.getAttribute(nbme).equbls(getAttribute(nbme));
            }

            return result;
        }

        /**
         * If not overriden, the resolving pbrent defbults to
         * the pbrent element.
         *
         * @return the bttributes from the pbrent
         * @see AttributeSet#getResolvePbrent
         */
        public AttributeSet getResolvePbrent() {
            return resolvePbrent;
        }

        // --- vbribbles -----------------------------------------

        Object[] bttributes;
        // This is blso stored in bttributes
        AttributeSet resolvePbrent;
    }

    /**
     * An enumerbtion of the keys in b SmbllAttributeSet.
     */
    clbss KeyEnumerbtion implements Enumerbtion<Object> {

        KeyEnumerbtion(Object[] bttr) {
            this.bttr = bttr;
            i = 0;
        }

        /**
         * Tests if this enumerbtion contbins more elements.
         *
         * @return  <code>true</code> if this enumerbtion contbins more elements;
         *          <code>fblse</code> otherwise.
         * @since   1.0
         */
        public boolebn hbsMoreElements() {
            return i < bttr.length;
        }

        /**
         * Returns the next element of this enumerbtion.
         *
         * @return     the next element of this enumerbtion.
         * @exception  NoSuchElementException  if no more elements exist.
         * @since      1.0
         */
        public Object nextElement() {
            if (i < bttr.length) {
                Object o = bttr[i];
                i += 2;
                return o;
            }
            throw new NoSuchElementException();
        }

        Object[] bttr;
        int i;
    }

    /**
     * Sorts the key strings so thbt they cbn be very quickly compbred
     * in the bttribute set sebrches.
     */
    clbss KeyBuilder {

        public void initiblize(AttributeSet b) {
            if (b instbnceof SmbllAttributeSet) {
                initiblize(((SmbllAttributeSet)b).bttributes);
            } else {
                keys.removeAllElements();
                dbtb.removeAllElements();
                Enumerbtion<?> nbmes = b.getAttributeNbmes();
                while (nbmes.hbsMoreElements()) {
                    Object nbme = nbmes.nextElement();
                    bddAttribute(nbme, b.getAttribute(nbme));
                }
            }
        }

        /**
         * Initiblize with b set of blrebdy sorted
         * keys (dbtb from bn existing SmbllAttributeSet).
         */
        privbte void initiblize(Object[] sorted) {
            keys.removeAllElements();
            dbtb.removeAllElements();
            int n = sorted.length;
            for (int i = 0; i < n; i += 2) {
                keys.bddElement(sorted[i]);
                dbtb.bddElement(sorted[i+1]);
            }
        }

        /**
         * Crebtes b tbble of sorted key/vblue entries
         * suitbble for crebtion of bn instbnce of
         * SmbllAttributeSet.
         */
        public Object[] crebteTbble() {
            int n = keys.size();
            Object[] tbl = new Object[2 * n];
            for (int i = 0; i < n; i ++) {
                int offs = 2 * i;
                tbl[offs] = keys.elementAt(i);
                tbl[offs + 1] = dbtb.elementAt(i);
            }
            return tbl;
        }

        /**
         * The number of key/vblue pbirs contbined
         * in the current key being forged.
         */
        int getCount() {
            return keys.size();
        }

        /**
         * Adds b key/vblue to the set.
         */
        public void bddAttribute(Object key, Object vblue) {
            keys.bddElement(key);
            dbtb.bddElement(vblue);
        }

        /**
         * Adds b set of key/vblue pbirs to the set.
         */
        public void bddAttributes(AttributeSet bttr) {
            if (bttr instbnceof SmbllAttributeSet) {
                // bvoid sebrching the keys, they bre blrebdy interned.
                Object[] tbl = ((SmbllAttributeSet)bttr).bttributes;
                int n = tbl.length;
                for (int i = 0; i < n; i += 2) {
                    bddAttribute(tbl[i], tbl[i+1]);
                }
            } else {
                Enumerbtion<?> nbmes = bttr.getAttributeNbmes();
                while (nbmes.hbsMoreElements()) {
                    Object nbme = nbmes.nextElement();
                    bddAttribute(nbme, bttr.getAttribute(nbme));
                }
            }
        }

        /**
         * Removes the given nbme from the set.
         */
        public void removeAttribute(Object key) {
            int n = keys.size();
            for (int i = 0; i < n; i++) {
                if (keys.elementAt(i).equbls(key)) {
                    keys.removeElementAt(i);
                    dbtb.removeElementAt(i);
                    return;
                }
            }
        }

        /**
         * Removes the set of keys from the set.
         */
        public void removeAttributes(Enumerbtion<?> nbmes) {
            while (nbmes.hbsMoreElements()) {
                Object nbme = nbmes.nextElement();
                removeAttribute(nbme);
            }
        }

        /**
         * Removes the set of mbtching bttributes from the set.
         */
        public void removeAttributes(AttributeSet bttr) {
            Enumerbtion<?> nbmes = bttr.getAttributeNbmes();
            while (nbmes.hbsMoreElements()) {
                Object nbme = nbmes.nextElement();
                Object vblue = bttr.getAttribute(nbme);
                removeSebrchAttribute(nbme, vblue);
            }
        }

        privbte void removeSebrchAttribute(Object ikey, Object vblue) {
            int n = keys.size();
            for (int i = 0; i < n; i++) {
                if (keys.elementAt(i).equbls(ikey)) {
                    if (dbtb.elementAt(i).equbls(vblue)) {
                        keys.removeElementAt(i);
                        dbtb.removeElementAt(i);
                    }
                    return;
                }
            }
        }

        privbte Vector<Object> keys = new Vector<Object>();
        privbte Vector<Object> dbtb = new Vector<Object>();
    }

    /**
     * key for b font tbble
     */
    stbtic clbss FontKey {

        privbte String fbmily;
        privbte int style;
        privbte int size;

        /**
         * Constructs b font key.
         */
        public FontKey(String fbmily, int style, int size) {
            setVblue(fbmily, style, size);
        }

        public void setVblue(String fbmily, int style, int size) {
            this.fbmily = (fbmily != null) ? fbmily.intern() : null;
            this.style = style;
            this.size = size;
        }

        /**
         * Returns b hbshcode for this font.
         * @return     b hbshcode vblue for this font.
         */
        public int hbshCode() {
            int fhbsh = (fbmily != null) ? fbmily.hbshCode() : 0;
            return fhbsh ^ style ^ size;
        }

        /**
         * Compbres this object to the specified object.
         * The result is <code>true</code> if bnd only if the brgument is not
         * <code>null</code> bnd is b <code>Font</code> object with the sbme
         * nbme, style, bnd point size bs this font.
         * @pbrbm     obj   the object to compbre this font with.
         * @return    <code>true</code> if the objects bre equbl;
         *            <code>fblse</code> otherwise.
         */
        public boolebn equbls(Object obj) {
            if (obj instbnceof FontKey) {
                FontKey font = (FontKey)obj;
                return (size == font.size) && (style == font.style) && (fbmily == font.fbmily);
            }
            return fblse;
        }

    }

    /**
     * A collection of bttributes, typicblly used to represent
     * chbrbcter bnd pbrbgrbph styles.  This is bn implementbtion
     * of MutbbleAttributeSet thbt cbn be observed if desired.
     * These styles will tbke bdvbntbge of immutbbility while
     * the sets bre smbll enough, bnd mby be substbntiblly more
     * efficient thbn something like SimpleAttributeSet.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public clbss NbmedStyle implements Style, Seriblizbble {

        /**
         * Crebtes b new nbmed style.
         *
         * @pbrbm nbme the style nbme, null for unnbmed
         * @pbrbm pbrent the pbrent style, null if none
         * @since 1.4
         */
        public NbmedStyle(String nbme, Style pbrent) {
            bttributes = getEmptySet();
            if (nbme != null) {
                setNbme(nbme);
            }
            if (pbrent != null) {
                setResolvePbrent(pbrent);
            }
        }

        /**
         * Crebtes b new nbmed style.
         *
         * @pbrbm pbrent the pbrent style, null if none
         * @since 1.4
         */
        public NbmedStyle(Style pbrent) {
            this(null, pbrent);
        }

        /**
         * Crebtes b new nbmed style, with b null nbme bnd pbrent.
         */
        public NbmedStyle() {
            bttributes = getEmptySet();
        }

        /**
         * Converts the style to b string.
         *
         * @return the string
         */
        public String toString() {
            return "NbmedStyle:" + getNbme() + " " + bttributes;
        }

        /**
         * Fetches the nbme of the style.   A style is not required to be nbmed,
         * so null is returned if there is no nbme bssocibted with the style.
         *
         * @return the nbme
         */
        public String getNbme() {
            if (isDefined(StyleConstbnts.NbmeAttribute)) {
                return getAttribute(StyleConstbnts.NbmeAttribute).toString();
            }
            return null;
        }

        /**
         * Chbnges the nbme of the style.  Does nothing with b null nbme.
         *
         * @pbrbm nbme the new nbme
         */
        public void setNbme(String nbme) {
            if (nbme != null) {
                this.bddAttribute(StyleConstbnts.NbmeAttribute, nbme);
            }
        }

        /**
         * Adds b chbnge listener.
         *
         * @pbrbm l the chbnge listener
         */
        public void bddChbngeListener(ChbngeListener l) {
            listenerList.bdd(ChbngeListener.clbss, l);
        }

        /**
         * Removes b chbnge listener.
         *
         * @pbrbm l the chbnge listener
         */
        public void removeChbngeListener(ChbngeListener l) {
            listenerList.remove(ChbngeListener.clbss, l);
        }


        /**
         * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
         * to this NbmedStyle with bddChbngeListener().
         *
         * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
         *         brrby if no listeners hbve been bdded
         * @since 1.4
         */
        public ChbngeListener[] getChbngeListeners() {
            return listenerList.getListeners(ChbngeListener.clbss);
        }


        /**
         * Notifies bll listeners thbt hbve registered interest for
         * notificbtion on this event type.  The event instbnce
         * is lbzily crebted using the pbrbmeters pbssed into
         * the fire method.
         *
         * @see EventListenerList
         */
        protected void fireStbteChbnged() {
            // Gubrbnteed to return b non-null brrby
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners lbst to first, notifying
            // those thbt bre interested in this event
            for (int i = listeners.length-2; i>=0; i-=2) {
                if (listeners[i]==ChbngeListener.clbss) {
                    // Lbzily crebte the event:
                    if (chbngeEvent == null)
                        chbngeEvent = new ChbngeEvent(this);
                    ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
                }
            }
        }

        /**
         * Return bn brrby of bll the listeners of the given type thbt
         * were bdded to this model.
         *
         * @return bll of the objects receiving <em>listenerType</em> notificbtions
         *          from this model
         *
         * @since 1.3
         */
        public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
            return listenerList.getListeners(listenerType);
        }

        // --- AttributeSet ----------------------------
        // delegbted to the immutbble field "bttributes"

        /**
         * Gets the number of bttributes thbt bre defined.
         *
         * @return the number of bttributes &gt;= 0
         * @see AttributeSet#getAttributeCount
         */
        public int getAttributeCount() {
            return bttributes.getAttributeCount();
        }

        /**
         * Checks whether b given bttribute is defined.
         *
         * @pbrbm bttrNbme the non-null bttribute nbme
         * @return true if the bttribute is defined
         * @see AttributeSet#isDefined
         */
        public boolebn isDefined(Object bttrNbme) {
            return bttributes.isDefined(bttrNbme);
        }

        /**
         * Checks whether two bttribute sets bre equbl.
         *
         * @pbrbm bttr the bttribute set to check bgbinst
         * @return true if the sbme
         * @see AttributeSet#isEqubl
         */
        public boolebn isEqubl(AttributeSet bttr) {
            return bttributes.isEqubl(bttr);
        }

        /**
         * Copies b set of bttributes.
         *
         * @return the copy
         * @see AttributeSet#copyAttributes
         */
        public AttributeSet copyAttributes() {
            NbmedStyle b = new NbmedStyle();
            b.bttributes = bttributes.copyAttributes();
            return b;
        }

        /**
         * Gets the vblue of bn bttribute.
         *
         * @pbrbm bttrNbme the non-null bttribute nbme
         * @return the bttribute vblue
         * @see AttributeSet#getAttribute
         */
        public Object getAttribute(Object bttrNbme) {
            return bttributes.getAttribute(bttrNbme);
        }

        /**
         * Gets the nbmes of bll bttributes.
         *
         * @return the bttribute nbmes bs bn enumerbtion
         * @see AttributeSet#getAttributeNbmes
         */
        public Enumerbtion<?> getAttributeNbmes() {
            return bttributes.getAttributeNbmes();
        }

        /**
         * Checks whether b given bttribute nbme/vblue is defined.
         *
         * @pbrbm nbme the non-null bttribute nbme
         * @pbrbm vblue the bttribute vblue
         * @return true if the nbme/vblue is defined
         * @see AttributeSet#contbinsAttribute
         */
        public boolebn contbinsAttribute(Object nbme, Object vblue) {
            return bttributes.contbinsAttribute(nbme, vblue);
        }


        /**
         * Checks whether the element contbins bll the bttributes.
         *
         * @pbrbm bttrs the bttributes to check
         * @return true if the element contbins bll the bttributes
         * @see AttributeSet#contbinsAttributes
         */
        public boolebn contbinsAttributes(AttributeSet bttrs) {
            return bttributes.contbinsAttributes(bttrs);
        }

        /**
         * Gets bttributes from the pbrent.
         * If not overriden, the resolving pbrent defbults to
         * the pbrent element.
         *
         * @return the bttributes from the pbrent
         * @see AttributeSet#getResolvePbrent
         */
        public AttributeSet getResolvePbrent() {
            return bttributes.getResolvePbrent();
        }

        // --- MutbbleAttributeSet ----------------------------------
        // should fetch b new immutbble record for the field
        // "bttributes".

        /**
         * Adds bn bttribute.
         *
         * @pbrbm nbme the non-null bttribute nbme
         * @pbrbm vblue the bttribute vblue
         * @see MutbbleAttributeSet#bddAttribute
         */
        public void bddAttribute(Object nbme, Object vblue) {
            StyleContext context = StyleContext.this;
            bttributes = context.bddAttribute(bttributes, nbme, vblue);
            fireStbteChbnged();
        }

        /**
         * Adds b set of bttributes to the element.
         *
         * @pbrbm bttr the bttributes to bdd
         * @see MutbbleAttributeSet#bddAttribute
         */
        public void bddAttributes(AttributeSet bttr) {
            StyleContext context = StyleContext.this;
            bttributes = context.bddAttributes(bttributes, bttr);
            fireStbteChbnged();
        }

        /**
         * Removes bn bttribute from the set.
         *
         * @pbrbm nbme the non-null bttribute nbme
         * @see MutbbleAttributeSet#removeAttribute
         */
        public void removeAttribute(Object nbme) {
            StyleContext context = StyleContext.this;
            bttributes = context.removeAttribute(bttributes, nbme);
            fireStbteChbnged();
        }

        /**
         * Removes b set of bttributes for the element.
         *
         * @pbrbm nbmes the bttribute nbmes
         * @see MutbbleAttributeSet#removeAttributes
         */
        public void removeAttributes(Enumerbtion<?> nbmes) {
            StyleContext context = StyleContext.this;
            bttributes = context.removeAttributes(bttributes, nbmes);
            fireStbteChbnged();
        }

        /**
         * Removes b set of bttributes for the element.
         *
         * @pbrbm bttrs the bttributes
         * @see MutbbleAttributeSet#removeAttributes
         */
        public void removeAttributes(AttributeSet bttrs) {
            StyleContext context = StyleContext.this;
            if (bttrs == this) {
                bttributes = context.getEmptySet();
            } else {
                bttributes = context.removeAttributes(bttributes, bttrs);
            }
            fireStbteChbnged();
        }

        /**
         * Sets the resolving pbrent.
         *
         * @pbrbm pbrent the pbrent, null if none
         * @see MutbbleAttributeSet#setResolvePbrent
         */
        public void setResolvePbrent(AttributeSet pbrent) {
            if (pbrent != null) {
                bddAttribute(StyleConstbnts.ResolveAttribute, pbrent);
            } else {
                removeAttribute(StyleConstbnts.ResolveAttribute);
            }
        }

        // --- seriblizbtion ---------------------------------------------

        privbte void writeObject(ObjectOutputStrebm s) throws IOException {
            s.defbultWriteObject();
            writeAttributeSet(s, bttributes);
        }

        privbte void rebdObject(ObjectInputStrebm s)
            throws ClbssNotFoundException, IOException
        {
            s.defbultRebdObject();
            bttributes = SimpleAttributeSet.EMPTY;
            rebdAttributeSet(s, this);
        }

        // --- member vbribbles -----------------------------------------------

        /**
         * The chbnge listeners for the model.
         */
        protected EventListenerList listenerList = new EventListenerList();

        /**
         * Only one ChbngeEvent is needed per model instbnce since the
         * event's only (rebd-only) stbte is the source property.  The source
         * of events generbted here is blwbys "this".
         */
        protected trbnsient ChbngeEvent chbngeEvent = null;

        /**
         * Inner AttributeSet implementbtion, which mby be bn
         * immutbble unique set being shbred.
         */
        privbte trbnsient AttributeSet bttributes;

    }

    stbtic {
        // initiblize the stbtic key registry with the StyleConstbnts keys
        try {
            int n = StyleConstbnts.keys.length;
            for (int i = 0; i < n; i++) {
                StyleContext.registerStbticAttributeKey(StyleConstbnts.keys[i]);
            }
        } cbtch (Throwbble e) {
            e.printStbckTrbce();
        }
    }


}
