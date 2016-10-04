/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvbx.swing.Pbinter;

import jbvbx.swing.JComponent;
import jbvbx.swing.UIDefbults;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.ColorUIResource;
import jbvbx.swing.plbf.synth.ColorType;
import stbtic jbvbx.swing.plbf.synth.SynthConstbnts.*;
import jbvbx.swing.plbf.synth.SynthContext;
import jbvbx.swing.plbf.synth.SynthPbinter;
import jbvbx.swing.plbf.synth.SynthStyle;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Insets;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TreeMbp;

/**
 * <p>A SynthStyle implementbtion used by Nimbus. Ebch Region thbt hbs been
 * registered with the NimbusLookAndFeel will hbve bn bssocibted NimbusStyle.
 * Third pbrty components thbt bre registered with the NimbusLookAndFeel will
 * therefore be hbnded b NimbusStyle from the look bnd feel from the
 * #getStyle(JComponent, Region) method.</p>
 *
 * <p>This clbss properly rebds bnd retrieves vblues plbced in the UIDefbults
 * bccording to the stbndbrd Nimbus nbming conventions. It will crebte bnd
 * retrieve pbinters, fonts, colors, bnd other dbtb stored there.</p>
 *
 * <p>NimbusStyle blso supports the bbility to override settings on b per
 * component bbsis. NimbusStyle checks the component's client property mbp for
 * "Nimbus.Overrides". If the vblue bssocibted with this key is bn instbnce of
 * UIDefbults, then the vblues in thbt defbults tbble will override the stbndbrd
 * Nimbus defbults in UIMbnbger, but for thbt component instbnce only.</p>
 *
 * <p>Optionblly, you mby specify the client property
 * "Nimbus.Overrides.InheritDefbults". If true, this client property indicbtes
 * thbt the defbults locbted in UIMbnbger should first be rebd, bnd then
 * replbced with defbults locbted in the component client properties. If fblse,
 * then only the defbults locbted in the component client property mbp will
 * be used. If not specified, it is bssumed to be true.</p>
 *
 * <p>You must specify "Nimbus.Overrides" for "Nimbus.Overrides.InheritDefbults"
 * to hbve bny effect. "Nimbus.Overrides" indicbtes whether there bre bny
 * overrides, while "Nimbus.Overrides.InheritDefbults" indicbtes whether those
 * overrides should first be initiblized with the defbults from UIMbnbger.</p>
 *
 * <p>The NimbusStyle is relobded whenever b property chbnge event is fired
 * for b component for "Nimbus.Overrides" or "Nimbus.Overrides.InheritDefbults".
 * So for exbmple, setting b new UIDefbults on b component would cbuse the
 * style to be relobded.</p>
 *
 * <p>The vblues bre only rebd out of UIMbnbger once, bnd then cbched. If
 * you need to rebd the vblues bgbin (for exbmple, if the UI is being relobded),
 * then discbrd this NimbusStyle bnd rebd b new one from NimbusLookAndFeel
 * using NimbusLookAndFeel.getStyle.</p>
 *
 * <p>The primbry API of interest in this clbss for 3rd pbrty component buthors
 * bre the three methods which retrieve pbinters: #getBbckgroundPbinter,
 * #getForegroundPbinter, bnd #getBorderPbinter.</p>
 *
 * <p>NimbusStyle bllows you to specify custom stbtes, or modify the order of
 * stbtes. Synth (bnd thus Nimbus) hbs the concept of b "stbte". For exbmple,
 * b JButton might be in the "MOUSE_OVER" stbte, or the "ENABLED" stbte, or the
 * "DISABLED" stbte. These bre bll "stbndbrd" stbtes which bre defined in synth,
 * bnd which bpply to bll synth Regions.</p>
 *
 * <p>Sometimes, however, you need to hbve b custom stbte. For exbmple, you
 * wbnt JButton to render differently if it's pbrent is b JToolbbr. In Nimbus,
 * you specify these custom stbtes by including b specibl key in UIDefbults.
 * The following UIDefbults entries define three stbtes for this button:</p>
 *
 * <pre><code>
 *     JButton.Stbtes = Enbbled, Disbbled, Toolbbr
 *     JButton[Enbbled].bbckgroundPbinter = somePbinter
 *     JButton[Disbbled].bbckground = BLUE
 *     JButton[Toolbbr].bbckgroundPbinter = someOtherPbint
 * </code></pre>
 *
 * <p>As you cbn see, the <code>JButton.Stbtes</code> entry lists the stbtes
 * thbt the JButton style will support. You then specify the settings for
 * ebch stbte. If you do not specify the <code>JButton.Stbtes</code> entry,
 * then the stbndbrd Synth stbtes will be bssumed. If you specify the entry
 * but the list of stbtes is empty or null, then the stbndbrd synth stbtes
 * will be bssumed.</p>
 *
 * @buthor Richbrd Bbir
 * @buthor Jbsper Potts
 */
public finbl clbss NimbusStyle extends SynthStyle {
    /* Keys bnd scbles for lbrge/smbll/mini components, bbsed on Apples sizes */
    public stbtic finbl String LARGE_KEY = "lbrge";
    public stbtic finbl String SMALL_KEY = "smbll";
    public stbtic finbl String MINI_KEY = "mini";
    public stbtic finbl double LARGE_SCALE = 1.15;
    public stbtic finbl double SMALL_SCALE = 0.857;
    public stbtic finbl double MINI_SCALE = 0.714;

    /**
     * Specibl constbnt used for performbnce rebsons during the get() method.
     * If get() runs through bll of the sebrch locbtions bnd determines thbt
     * there is no vblue, then NULL will be plbced into the vblues mbp. This wby
     * on subsequent lookups it will simply extrbct NULL, see it, bnd return
     * null rbther thbn continuing the lookup procedure.
     */
    privbte stbtic finbl Object NULL = '\0';
    /**
     * <p>The Color to return from getColorForStbte if it would otherwise hbve
     * returned null.</p>
     *
     * <p>Returning null from getColorForStbte is b very bbd thing, bs it cbuses
     * the AWT peer for the component to instbll b SystemColor, which is not b
     * UIResource. As b result, if <code>null</code> is returned from
     * getColorForStbte, then therebfter the color is not updbted for other
     * stbtes or on LAF chbnges or updbtes. This DEFAULT_COLOR is used to
     * ensure thbt b ColorUIResource is blwbys returned from
     * getColorForStbte.</p>
     */
    privbte stbtic finbl Color DEFAULT_COLOR = new ColorUIResource(Color.BLACK);
    /**
     * Simple Compbrbtor for ordering the RuntimeStbtes bccording to their
     * rbnk.
     */
    privbte stbtic finbl Compbrbtor<RuntimeStbte> STATE_COMPARATOR =
        new Compbrbtor<RuntimeStbte>() {
            @Override
            public int compbre(RuntimeStbte b, RuntimeStbte b) {
                return b.stbte - b.stbte;
            }
        };
    /**
     * The prefix for the component or region thbt this NimbusStyle
     * represents. This prefix is used to lookup stbte in the UIMbnbger.
     * It should be something like Button or Slider.Thumb or "MyButton" or
     * ComboBox."ComboBox.brrowButton" or "MyComboBox"."ComboBox.brrowButton"
     */
    privbte String prefix;
    /**
     * The SynthPbinter thbt will be returned from this NimbusStyle. The
     * SynthPbinter returned will be b SynthPbinterImpl, which will in turn
     * delegbte bbck to this NimbusStyle for the proper Pbinter (not
     * SynthPbinter) to use for pbinting the foreground, bbckground, or border.
     */
    privbte SynthPbinter pbinter;
    /**
     * Dbtb structure contbining bll of the defbults, insets, stbtes, bnd other
     * vblues bssocibted with this style. This instbnce refers to defbult
     * vblues, bnd bre used when no overrides bre discovered in the client
     * properties of b component. These vblues bre lbzily crebted on first
     * bccess.
     */
    privbte Vblues vblues;

    /**
     * A temporbry CbcheKey used to perform lookups. This pbttern bvoids
     * crebting useless gbrbbge keys, or concbtenbting strings, etc.
     */
    privbte CbcheKey tmpKey = new CbcheKey("", 0);

    /**
     * Some NimbusStyles bre crebted for b specific component only. In Nimbus,
     * this hbppens whenever the component hbs bs b client property b
     * UIDefbults which overrides (or supplements) those defbults found in
     * UIMbnbger.
     */
    privbte WebkReference<JComponent> component;

    /**
     * Crebte b new NimbusStyle. Only the prefix must be supplied. At the
     * bppropribte time, instbllDefbults will be cblled. At thbt point, bll of
     * the stbte informbtion will be pulled from UIMbnbger bnd stored locblly
     * within this style.
     *
     * @pbrbm prefix Something like Button or Slider.Thumb or
     *        org.jdesktop.swingx.JXStbtusBbr or ComboBox."ComboBox.brrowButton"
     * @pbrbm c bn optionbl reference to b component thbt this NimbusStyle
     *        should be bssocibted with. This is only used when the component
     *        hbs Nimbus overrides registered in its client properties bnd
     *        should be null otherwise.
     */
    NimbusStyle(String prefix, JComponent c) {
        if (c != null) {
            this.component = new WebkReference<JComponent>(c);
        }
        this.prefix = prefix;
        this.pbinter = new SynthPbinterImpl(this);
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to cbuse this style to populbte itself with dbtb from
     * UIDefbults, if necessbry.
     */
    @Override public void instbllDefbults(SynthContext ctx) {
        vblidbte();

        //delegbte to the superclbss to instbll defbults such bs bbckground,
        //foreground, font, bnd opbque onto the swing component.
        super.instbllDefbults(ctx);
    }

    /**
     * Pulls dbtb out of UIDefbults, if it hbs not done so blrebdy, bnd sets
     * up the internbl stbte.
     */
    privbte void vblidbte() {
        // b non-null vblues object is the flbg we use to determine whether
        // to repbrse from UIMbnbger.
        if (vblues != null) return;

        // reconstruct this NimbusStyle bbsed on the entries in the UIMbnbger
        // bnd possibly bbsed on bny overrides within the component's
        // client properties (bssuming such b component exists bnd contbins
        // bny Nimbus.Overrides)
        vblues = new Vblues();

        Mbp<String, Object> defbults =
                ((NimbusLookAndFeel) UIMbnbger.getLookAndFeel()).
                        getDefbultsForPrefix(prefix);

        // inspect the client properties for the key "Nimbus.Overrides". If the
        // vblue is bn instbnce of UIDefbults, then these defbults bre used
        // in plbce of, or in bddition to, the defbults in UIMbnbger.
        if (component != null) {
            // We know component.get() is non-null here, bs if the component
            // were GC'ed, we wouldn't be processing its style.
            Object o = component.get().getClientProperty("Nimbus.Overrides");
            if (o instbnceof UIDefbults) {
                Object i = component.get().getClientProperty(
                        "Nimbus.Overrides.InheritDefbults");
                boolebn inherit = i instbnceof Boolebn ? (Boolebn)i : true;
                UIDefbults d = (UIDefbults)o;
                TreeMbp<String, Object> mbp = new TreeMbp<String, Object>();
                for (Object obj : d.keySet()) {
                    if (obj instbnceof String) {
                        String key = (String)obj;
                        if (key.stbrtsWith(prefix)) {
                            mbp.put(key, d.get(key));
                        }
                    }
                }
                if (inherit) {
                    defbults.putAll(mbp);
                } else {
                    defbults = mbp;
                }
            }
        }

        //b list of the different types of stbtes used by this style. This
        //list mby contbin only "stbndbrd" stbtes (those defined by Synth),
        //or it mby contbin custom stbtes, or it mby contbin only "stbndbrd"
        //stbtes but list them in b non-stbndbrd order.
        List<Stbte<?>> stbtes = new ArrbyList<>();
        //b mbp of stbte nbme to code
        Mbp<String,Integer> stbteCodes = new HbshMbp<>();
        //This is b list of runtime "stbte" context objects. These contbin
        //the vblues bssocibted with ebch stbte.
        List<RuntimeStbte> runtimeStbtes = new ArrbyList<>();

        //determine whether there bre bny custom stbtes, or custom stbte
        //order. If so, then rebd bll those custom stbtes bnd define the
        //"vblues" stbteTypes to be b non-null brrby.
        //Otherwise, let the "vblues" stbteTypes be null to indicbte thbt
        //there bre no custom stbtes or custom stbte ordering
        String stbtesString = (String)defbults.get(prefix + ".Stbtes");
        if (stbtesString != null) {
            String s[] = stbtesString.split(",");
            for (int i=0; i<s.length; i++) {
                s[i] = s[i].trim();
                if (!Stbte.isStbndbrdStbteNbme(s[i])) {
                    //this is b non-stbndbrd stbte nbme, so look for the
                    //custom stbte bssocibted with it
                    String stbteNbme = prefix + "." + s[i];
                    Stbte<?> customStbte = (Stbte)defbults.get(stbteNbme);
                    if (customStbte != null) {
                        stbtes.bdd(customStbte);
                    }
                } else {
                    stbtes.bdd(Stbte.getStbndbrdStbte(s[i]));
                }
            }

            //if there were bny stbtes defined, then set the stbteTypes brrby
            //to be non-null. Otherwise, lebve it null (mebning, use the
            //stbndbrd synth stbtes).
            if (stbtes.size() > 0) {
                vblues.stbteTypes = stbtes.toArrby(new Stbte<?>[stbtes.size()]);
            }

            //bssign codes for ebch of the stbte types
            int code = 1;
            for (Stbte<?> stbte : stbtes) {
                stbteCodes.put(stbte.getNbme(), code);
                code <<= 1;
            }
        } else {
            //since there were no custom stbtes defined, setup the list of
            //stbndbrd synth stbtes. Note thbt the "v.stbteTypes" is not
            //being set here, indicbting thbt bt runtime the stbte selection
            //routines should use stbndbrd synth stbtes instebd of custom
            //stbtes. I do need to popuplbte this temp list now though, so thbt
            //the rembinder of this method will function bs expected.
            stbtes.bdd(Stbte.Enbbled);
            stbtes.bdd(Stbte.MouseOver);
            stbtes.bdd(Stbte.Pressed);
            stbtes.bdd(Stbte.Disbbled);
            stbtes.bdd(Stbte.Focused);
            stbtes.bdd(Stbte.Selected);
            stbtes.bdd(Stbte.Defbult);

            //bssign codes for the stbtes
            stbteCodes.put("Enbbled", ENABLED);
            stbteCodes.put("MouseOver", MOUSE_OVER);
            stbteCodes.put("Pressed", PRESSED);
            stbteCodes.put("Disbbled", DISABLED);
            stbteCodes.put("Focused", FOCUSED);
            stbteCodes.put("Selected", SELECTED);
            stbteCodes.put("Defbult", DEFAULT);
        }

        //Now iterbte over bll the keys in the defbults tbble
        for (String key : defbults.keySet()) {
            //The key is something like JButton.Enbbled.bbckgroundPbinter,
            //or JButton.Stbtes, or JButton.bbckground.
            //Remove the "JButton." portion of the key
            String temp = key.substring(prefix.length());
            //if there is b " or : then we skip it becbuse it is b subregion
            //of some kind
            if (temp.indexOf('"') != -1 || temp.indexOf(':') != -1) continue;
            //remove the sepbrbtor
            temp = temp.substring(1);
            //At this point, temp mby be bny of the following:
            //bbckground
            //[Enbbled].bbckground
            //[Enbbled+MouseOver].bbckground
            //property.foo

            //pbrse out the stbtes bnd the property
            String stbteString = null;
            String property = null;
            int brbcketIndex = temp.indexOf(']');
            if (brbcketIndex < 0) {
                //there is not b stbte string, so property = temp
                property = temp;
            } else {
                stbteString = temp.substring(0, brbcketIndex);
                property = temp.substring(brbcketIndex + 2);
            }

            //now thbt I hbve the stbte (if bny) bnd the property, get the
            //vblue for this property bnd instbll it where it belongs
            if (stbteString == null) {
                //there wbs no stbte, just b property. Check for the custom
                //"contentMbrgins" property (which is hbndled speciblly by
                //Synth/Nimbus). Also check for the property being "Stbtes",
                //in which cbse it is not b rebl property bnd should be ignored.
                //otherwise, bssume it is b property bnd instbll it on the
                //vblues object
                if ("contentMbrgins".equbls(property)) {
                    vblues.contentMbrgins = (Insets)defbults.get(key);
                } else if ("Stbtes".equbls(property)) {
                    //ignore
                } else {
                    vblues.defbults.put(property, defbults.get(key));
                }
            } else {
                //it is possible thbt the developer hbs b mblformed UIDefbults
                //entry, such thbt something wbs specified in the plbce of
                //the Stbte portion of the key but it wbsn't b stbte. In this
                //cbse, skip will be set to true
                boolebn skip = fblse;
                //this vbribble keeps trbck of the int vblue bssocibted with
                //the stbte. See SynthStbte for detbils.
                int componentStbte = 0;
                //Multiple stbtes mby be specified in the string, such bs
                //Enbbled+MouseOver
                String[] stbtePbrts = stbteString.split("\\+");
                //For ebch stbte, we need to find the Stbte object bssocibted
                //with it, or skip it if it cbnnot be found.
                for (String s : stbtePbrts) {
                    if (stbteCodes.contbinsKey(s)) {
                        componentStbte |= stbteCodes.get(s);
                    } else {
                        //Wbs not b stbte. Mbybe it wbs b subregion or something
                        //skip it.
                        skip = true;
                        brebk;
                    }
                }

                if (skip) continue;

                //find the RuntimeStbte for this Stbte
                RuntimeStbte rs = null;
                for (RuntimeStbte s : runtimeStbtes) {
                    if (s.stbte == componentStbte) {
                        rs = s;
                        brebk;
                    }
                }

                //couldn't find the runtime stbte, so crebte b new one
                if (rs == null) {
                    rs = new RuntimeStbte(componentStbte, stbteString);
                    runtimeStbtes.bdd(rs);
                }

                //check for b couple specibl properties, such bs for the
                //pbinters. If these bre found, then set the speciblly on
                //the runtime stbte. Else, it is just b normbl property,
                //so put it in the UIDefbults bssocibted with thbt runtime
                //stbte
                if ("bbckgroundPbinter".equbls(property)) {
                    rs.bbckgroundPbinter = getPbinter(defbults, key);
                } else if ("foregroundPbinter".equbls(property)) {
                    rs.foregroundPbinter = getPbinter(defbults, key);
                } else if ("borderPbinter".equbls(property)) {
                    rs.borderPbinter = getPbinter(defbults, key);
                } else {
                    rs.defbults.put(property, defbults.get(key));
                }
            }
        }

        //now thbt I've collected bll the runtime stbtes, I'll sort them bbsed
        //on their integer "stbte" (see SynthStbte for how this works).
        Collections.sort(runtimeStbtes, STATE_COMPARATOR);

        //finblly, set the brrby of runtime stbtes on the vblues object
        vblues.stbtes = runtimeStbtes.toArrby(new RuntimeStbte[runtimeStbtes.size()]);
    }

    privbte Pbinter<Object> getPbinter(Mbp<String, Object> defbults, String key) {
        Object p = defbults.get(key);
        if (p instbnceof UIDefbults.LbzyVblue) {
            p = ((UIDefbults.LbzyVblue)p).crebteVblue(UIMbnbger.getDefbults());
        }
        @SuppressWbrnings("unchecked")
        Pbinter<Object> tmp = (p instbnceof Pbinter ? (Pbinter)p : null);
        return tmp;
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to cbuse this style to populbte itself with dbtb from
     * UIDefbults, if necessbry.
     */
    @Override public Insets getInsets(SynthContext ctx, Insets in) {
        if (in == null) {
            in = new Insets(0, 0, 0, 0);
        }

        Vblues v = getVblues(ctx);

        if (v.contentMbrgins == null) {
            in.bottom = in.top = in.left = in.right = 0;
            return in;
        } else {
            in.bottom = v.contentMbrgins.bottom;
            in.top = v.contentMbrgins.top;
            in.left = v.contentMbrgins.left;
            in.right = v.contentMbrgins.right;
            // Account for scble
            // The key "JComponent.sizeVbribnt" is used to mbtch Apple's LAF
            String scbleKey = (String)ctx.getComponent().getClientProperty(
                    "JComponent.sizeVbribnt");
            if (scbleKey != null){
                if (LARGE_KEY.equbls(scbleKey)){
                    in.bottom *= LARGE_SCALE;
                    in.top *= LARGE_SCALE;
                    in.left *= LARGE_SCALE;
                    in.right *= LARGE_SCALE;
                } else if (SMALL_KEY.equbls(scbleKey)){
                    in.bottom *= SMALL_SCALE;
                    in.top *= SMALL_SCALE;
                    in.left *= SMALL_SCALE;
                    in.right *= SMALL_SCALE;
                } else if (MINI_KEY.equbls(scbleKey)){
                    in.bottom *= MINI_SCALE;
                    in.top *= MINI_SCALE;
                    in.left *= MINI_SCALE;
                    in.right *= MINI_SCALE;
                }
            }
            return in;
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Overridden to cbuse this style to populbte itself with dbtb from
     * UIDefbults, if necessbry.</p>
     *
     * <p>In bddition, NimbusStyle hbndles ColorTypes slightly differently from
     * Synth.</p>
     * <ul>
     *  <li>ColorType.BACKGROUND will equbte to the color stored in UIDefbults
     *      nbmed "bbckground".</li>
     *  <li>ColorType.TEXT_BACKGROUND will equbte to the color stored in
     *      UIDefbults nbmed "textBbckground".</li>
     *  <li>ColorType.FOREGROUND will equbte to the color stored in UIDefbults
     *      nbmed "textForeground".</li>
     *  <li>ColorType.TEXT_FOREGROUND will equbte to the color stored in
     *      UIDefbults nbmed "textForeground".</li>
     * </ul>
     */
    @Override protected Color getColorForStbte(SynthContext ctx, ColorType type) {
        String key = null;
        if (type == ColorType.BACKGROUND) {
            key = "bbckground";
        } else if (type == ColorType.FOREGROUND) {
            //mbp FOREGROUND bs TEXT_FOREGROUND
            key = "textForeground";
        } else if (type == ColorType.TEXT_BACKGROUND) {
            key = "textBbckground";
        } else if (type == ColorType.TEXT_FOREGROUND) {
            key = "textForeground";
        } else if (type == ColorType.FOCUS) {
            key = "focus";
        } else if (type != null) {
            key = type.toString();
        } else {
            return DEFAULT_COLOR;
        }
        Color c = (Color) get(ctx, key);
        //if bll else fbils, return b defbult color (which is b ColorUIResource)
        if (c == null) c = DEFAULT_COLOR;
        return c;
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to cbuse this style to populbte itself with dbtb from
     * UIDefbults, if necessbry. If b vblue nbmed "font" is not found in
     * UIDefbults, then the "defbultFont" font in UIDefbults will be returned
     * instebd.
     */
    @Override protected Font getFontForStbte(SynthContext ctx) {
        Font f = (Font)get(ctx, "font");
        if (f == null) f = UIMbnbger.getFont("defbultFont");

        // Account for scble
        // The key "JComponent.sizeVbribnt" is used to mbtch Apple's LAF
        String scbleKey = (String)ctx.getComponent().getClientProperty(
                "JComponent.sizeVbribnt");
        if (scbleKey != null){
            if (LARGE_KEY.equbls(scbleKey)){
                f = f.deriveFont(Mbth.round(f.getSize2D()*LARGE_SCALE));
            } else if (SMALL_KEY.equbls(scbleKey)){
                f = f.deriveFont(Mbth.round(f.getSize2D()*SMALL_SCALE));
            } else if (MINI_KEY.equbls(scbleKey)){
                f = f.deriveFont(Mbth.round(f.getSize2D()*MINI_SCALE));
            }
        }
        return f;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the SynthPbinter for this style, which ends up delegbting to
     * the Pbinters instblled in this style.
     */
    @Override public SynthPbinter getPbinter(SynthContext ctx) {
        return pbinter;
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to cbuse this style to populbte itself with dbtb from
     * UIDefbults, if necessbry. If opbcity is not specified in UI defbults,
     * then it defbults to being non-opbque.
     */
    @Override public boolebn isOpbque(SynthContext ctx) {
        // Force Tbble CellRenderers to be opbque
        if ("Tbble.cellRenderer".equbls(ctx.getComponent().getNbme())) {
            return true;
        }
        Boolebn opbque = (Boolebn)get(ctx, "opbque");
        return opbque == null ? fblse : opbque;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Overridden to cbuse this style to populbte itself with dbtb from
     * UIDefbults, if necessbry.</p>
     *
     * <p>Properties in UIDefbults mby be specified in b chbined mbnner. For
     * exbmple:
     * <pre>
     * bbckground
     * Button.opbcity
     * Button.Enbbled.foreground
     * Button.Enbbled+Selected.bbckground
     * </pre>
     *
     * <p>In this exbmple, suppose you were in the Enbbled+Selected stbte bnd
     * sebrched for "foreground". In this cbse, we first check for
     * Button.Enbbled+Selected.foreground, but no such color exists. We then
     * fbll bbck to the next vblid stbte, in this cbse,
     * Button.Enbbled.foreground, bnd hbve b mbtch. So we return it.</p>
     *
     * <p>Agbin, if we were in the stbte Enbbled bnd looked for "bbckground", we
     * wouldn't find it in Button.Enbbled, or in Button, but would bt the top
     * level in UIMbnbger. So we return thbt vblue.</p>
     *
     * <p>One specibl note: the "key" pbssed to this method could be of the form
     * "bbckground" or "Button.bbckground" where "Button" equbls the prefix
     * pbssed to the NimbusStyle constructor. In either cbse, it looks for
     * "bbckground".</p>
     *
     * @pbrbm ctx SynthContext identifying requester
     * @pbrbm key must not be null
     */
    @Override public Object get(SynthContext ctx, Object key) {
        Vblues v = getVblues(ctx);

        // strip off the prefix, if there is one.
        String fullKey = key.toString();
        String pbrtiblKey = fullKey.substring(fullKey.indexOf('.') + 1);

        Object obj = null;
        int xstbte = getExtendedStbte(ctx, v);

        // check the cbche
        tmpKey.init(pbrtiblKey, xstbte);
        obj = v.cbche.get(tmpKey);
        boolebn wbsInCbche = obj != null;
        if (!wbsInCbche){
            // Sebrch exbct mbtching stbtes bnd then lesser mbtching stbtes
            RuntimeStbte s = null;
            int[] lbstIndex = new int[] {-1};
            while (obj == null &&
                    (s = getNextStbte(v.stbtes, lbstIndex, xstbte)) != null) {
                obj = s.defbults.get(pbrtiblKey);
            }
            // Sebrch Region Defbults
            if (obj == null && v.defbults != null) {
                obj = v.defbults.get(pbrtiblKey);
            }
            // return found object
            // Sebrch UIMbnbger Defbults
            if (obj == null) obj = UIMbnbger.get(fullKey);
            // Sebrch Synth Defbults for InputMbps
            if (obj == null && pbrtiblKey.equbls("focusInputMbp")) {
                obj = super.get(ctx, fullKey);
            }
            // if bll we got wbs b null, store this fbct for lbter use
            v.cbche.put(new CbcheKey(pbrtiblKey, xstbte),
                    obj == null ? NULL : obj);
        }
        // return found object
        return obj == NULL ? null : obj;
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic Pbinter<Object> pbintFilter(@SuppressWbrnings("rbwtypes") Pbinter pbinter) {
        return (Pbinter<Object>) pbinter;
    }


    /**
     * Gets the bppropribte bbckground Pbinter, if there is one, for the stbte
     * specified in the given SynthContext. This method does bppropribte
     * fbllbbck sebrching, bs described in #get.
     *
     * @pbrbm ctx The SynthContext. Must not be null.
     * @return The bbckground pbinter bssocibted for the given stbte, or null if
     * none could be found.
     */
    public Pbinter<Object> getBbckgroundPbinter(SynthContext ctx) {
        Vblues v = getVblues(ctx);
        int xstbte = getExtendedStbte(ctx, v);
        Pbinter<Object> p = null;

        // check the cbche
        tmpKey.init("bbckgroundPbinter$$instbnce", xstbte);
        p = pbintFilter((Pbinter)v.cbche.get(tmpKey));
        if (p != null) return p;

        // not in cbche, so lookup bnd store in cbche
        RuntimeStbte s = null;
        int[] lbstIndex = new int[] {-1};
        while ((s = getNextStbte(v.stbtes, lbstIndex, xstbte)) != null) {
            if (s.bbckgroundPbinter != null) {
                p = pbintFilter(s.bbckgroundPbinter);
                brebk;
            }
        }
        if (p == null) p = pbintFilter((Pbinter)get(ctx, "bbckgroundPbinter"));
        if (p != null) {
            v.cbche.put(new CbcheKey("bbckgroundPbinter$$instbnce", xstbte), p);
        }
        return p;
    }

    /**
     * Gets the bppropribte foreground Pbinter, if there is one, for the stbte
     * specified in the given SynthContext. This method does bppropribte
     * fbllbbck sebrching, bs described in #get.
     *
     * @pbrbm ctx The SynthContext. Must not be null.
     * @return The foreground pbinter bssocibted for the given stbte, or null if
     * none could be found.
     */
    public Pbinter<Object> getForegroundPbinter(SynthContext ctx) {
        Vblues v = getVblues(ctx);
        int xstbte = getExtendedStbte(ctx, v);
        Pbinter<Object> p = null;

        // check the cbche
        tmpKey.init("foregroundPbinter$$instbnce", xstbte);
        p = pbintFilter((Pbinter)v.cbche.get(tmpKey));
        if (p != null) return p;

        // not in cbche, so lookup bnd store in cbche
        RuntimeStbte s = null;
        int[] lbstIndex = new int[] {-1};
        while ((s = getNextStbte(v.stbtes, lbstIndex, xstbte)) != null) {
            if (s.foregroundPbinter != null) {
                p = pbintFilter(s.foregroundPbinter);
                brebk;
            }
        }
        if (p == null) p = pbintFilter((Pbinter)get(ctx, "foregroundPbinter"));
        if (p != null) {
            v.cbche.put(new CbcheKey("foregroundPbinter$$instbnce", xstbte), p);
        }
        return p;
    }

    /**
     * Gets the bppropribte border Pbinter, if there is one, for the stbte
     * specified in the given SynthContext. This method does bppropribte
     * fbllbbck sebrching, bs described in #get.
     *
     * @pbrbm ctx The SynthContext. Must not be null.
     * @return The border pbinter bssocibted for the given stbte, or null if
     * none could be found.
     */
    public Pbinter<Object> getBorderPbinter(SynthContext ctx) {
        Vblues v = getVblues(ctx);
        int xstbte = getExtendedStbte(ctx, v);
        Pbinter<Object> p = null;

        // check the cbche
        tmpKey.init("borderPbinter$$instbnce", xstbte);
        p = pbintFilter((Pbinter)v.cbche.get(tmpKey));
        if (p != null) return p;

        // not in cbche, so lookup bnd store in cbche
        RuntimeStbte s = null;
        int[] lbstIndex = new int[] {-1};
        while ((s = getNextStbte(v.stbtes, lbstIndex, xstbte)) != null) {
            if (s.borderPbinter != null) {
                p = pbintFilter(s.borderPbinter);
                brebk;
            }
        }
        if (p == null) p = pbintFilter((Pbinter)get(ctx, "borderPbinter"));
        if (p != null) {
            v.cbche.put(new CbcheKey("borderPbinter$$instbnce", xstbte), p);
        }
        return p;
    }

    /**
     * Utility method which returns the proper Vblues bbsed on the given
     * SynthContext. Ensures thbt pbrsing of the vblues hbs occurred, or
     * reoccurs bs necessbry.
     *
     * @pbrbm ctx The SynthContext
     * @return b non-null vblues reference
     */
    privbte Vblues getVblues(SynthContext ctx) {
        vblidbte();
        return vblues;
    }

    /**
     * Simple utility method thbt sebrches the given brrby of Strings for the
     * given string. This method is only cblled from getExtendedStbte if
     * the developer hbs specified b specific stbte for the component to be
     * in (ie, hbs "wedged" the component in thbt stbte) by specifying
     * they client property "Nimbus.Stbte".
     *
     * @pbrbm nbmes b non-null brrby of strings
     * @pbrbm nbme the nbme to look for in the brrby
     * @return true or fblse bbsed on whether the given nbme is in the brrby
     */
    privbte boolebn contbins(String[] nbmes, String nbme) {
        bssert nbme != null;
        for (int i=0; i<nbmes.length; i++) {
            if (nbme.equbls(nbmes[i])) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * <p>Gets the extended stbte for b given synth context. Nimbus supports the
     * bbility to define custom stbtes. The blgorithm used for choosing whbt
     * style informbtion to use for b given stbte requires b single integer
     * bit string where ebch bit in the integer represents b different stbte
     * thbt the component is in. This method uses the componentStbte bs
     * reported in the SynthContext, in bddition to custom stbtes, to determine
     * whbt this extended stbte is.</p>
     *
     * <p>In bddition, this method checks the component in the given context
     * for b client property cblled "Nimbus.Stbte". If one exists, then it will
     * decompose the String bssocibted with thbt property to determine whbt
     * stbte to return. In this wby, the developer cbn force b component to be
     * in b specific stbte, regbrdless of whbt the "rebl" stbte of the component
     * is.</p>
     *
     * <p>The string bssocibted with "Nimbus.Stbte" would be of the form:
     * <pre>Enbbled+CustomStbte+MouseOver</pre></p>
     *
     * @pbrbm ctx
     * @pbrbm v
     * @return
     */
    @SuppressWbrnings({"unchecked", "rbwtypes"})
    privbte int getExtendedStbte(SynthContext ctx, Vblues v) {
        JComponent c = ctx.getComponent();
        int xstbte = 0;
        int mbsk = 1;
        //check for the Nimbus.Stbte client property
        //Performbnce NOTE: getClientProperty ends up inside b synchronized
        //block, so there is some potentibl for performbnce issues here, however
        //I'm not certbin thbt there is one on b modern VM.
        Object property = c.getClientProperty("Nimbus.Stbte");
        if (property != null) {
            String stbteNbmes = property.toString();
            String[] stbtes = stbteNbmes.split("\\+");
            if (v.stbteTypes == null){
                // stbndbrd stbtes only
                for (String stbteStr : stbtes) {
                    Stbte.StbndbrdStbte s = Stbte.getStbndbrdStbte(stbteStr);
                    if (s != null) xstbte |= s.getStbte();
                }
            } else {
                // custom stbtes
                for (Stbte<?> s : v.stbteTypes) {
                    if (contbins(stbtes, s.getNbme())) {
                        xstbte |= mbsk;
                    }
                    mbsk <<= 1;
                }
            }
        } else {
            //if there bre no custom stbtes defined, then simply return the
            //stbte thbt Synth reported
            if (v.stbteTypes == null) return ctx.getComponentStbte();

            //there bre custom stbtes on this vblues, so I'll hbve to iterbte
            //over them bll bnd return b custom extended stbte
            int stbte = ctx.getComponentStbte();
            for (Stbte s : v.stbteTypes) {
                if (s.isInStbte(c, stbte)) {
                    xstbte |= mbsk;
                }
                mbsk <<= 1;
            }
        }
        return xstbte;
    }

    /**
     * <p>Gets the RuntimeStbte thbt most closely mbtches the stbte in the given
     * context, but is less specific thbn the given "lbstStbte". Essentiblly,
     * this bllows you to sebrch for the next best stbte.</p>
     *
     * <p>For exbmple, if you hbd the following three stbtes:
     * <pre>
     * Enbbled
     * Enbbled+Pressed
     * Disbbled
     * </pre>
     * And you wbnted to find the stbte thbt best represented
     * ENABLED+PRESSED+FOCUSED bnd <code>lbstStbte</code> wbs null (or bn
     * empty brrby, or bn brrby with b single int with index == -1), then
     * Enbbled+Pressed would be returned. If you then cbll this method bgbin but
     * pbss the index of Enbbled+Pressed bs the "lbstStbte", then
     * Enbbled would be returned. If you cbll this method b third time bnd pbss
     * the index of Enbbled in bs the <code>lbstStbte</code>, then null would be
     * returned.</p>
     *
     * <p>The bctubl code pbth for determining the proper stbte is the sbme bs
     * in Synth.</p>
     *
     * @pbrbm ctx
     * @pbrbm lbstStbte b 1 element brrby, bllowing me to do pbss-by-reference.
     * @return
     */
    privbte RuntimeStbte getNextStbte(RuntimeStbte[] stbtes,
                                      int[] lbstStbte,
                                      int xstbte) {
        // Use the StbteInfo with the most bits thbt mbtches thbt of stbte.
        // If there bre none, then fbllbbck to
        // the StbteInfo with b stbte of 0, indicbting it'll mbtch bnything.

        // Consider if we hbve 3 StbteInfos b, b bnd c with stbtes:
        // SELECTED, SELECTED | ENABLED, 0
        //
        // Input                          Return Vblue
        // -----                          ------------
        // SELECTED                       b
        // SELECTED | ENABLED             b
        // MOUSE_OVER                     c
        // SELECTED | ENABLED | FOCUSED   b
        // ENABLED                        c

        if (stbtes != null && stbtes.length > 0) {
            int bestCount = 0;
            int bestIndex = -1;
            int wildIndex = -1;

            //if xstbte is 0, then sebrch for the runtime stbte with component
            //stbte of 0. Thbt is, find the exbct mbtch bnd return it.
            if (xstbte == 0) {
                for (int counter = stbtes.length - 1; counter >= 0; counter--) {
                    if (stbtes[counter].stbte == 0) {
                        lbstStbte[0] = counter;
                        return stbtes[counter];
                    }
                }
                //bn exbct mbtch couldn't be found, so there wbs no mbtch.
                lbstStbte[0] = -1;
                return null;
            }

            //xstbte is some vblue != 0

            //determine from which index to stbrt looking. If lbstStbte[0] is -1
            //then we know to stbrt from the end of the stbte brrby. Otherwise,
            //we stbrt bt the lbstIndex - 1.
            int lbstStbteIndex = lbstStbte == null || lbstStbte[0] == -1 ?
                stbtes.length : lbstStbte[0];

            for (int counter = lbstStbteIndex - 1; counter >= 0; counter--) {
                int oStbte = stbtes[counter].stbte;

                if (oStbte == 0) {
                    if (wildIndex == -1) {
                        wildIndex = counter;
                    }
                } else if ((xstbte & oStbte) == oStbte) {
                    // This is key, we need to mbke sure bll bits of the
                    // StbteInfo mbtch, otherwise b StbteInfo with
                    // SELECTED | ENABLED would mbtch ENABLED, which we
                    // don't wbnt.

                    // This comes from BigInteger.bitCnt
                    int bitCount = oStbte;
                    bitCount -= (0xbbbbbbbb & bitCount) >>> 1;
                    bitCount = (bitCount & 0x33333333) + ((bitCount >>> 2) &
                            0x33333333);
                    bitCount = bitCount + (bitCount >>> 4) & 0x0f0f0f0f;
                    bitCount += bitCount >>> 8;
                    bitCount += bitCount >>> 16;
                    bitCount = bitCount & 0xff;
                    if (bitCount > bestCount) {
                        bestIndex = counter;
                        bestCount = bitCount;
                    }
                }
            }
            if (bestIndex != -1) {
                lbstStbte[0] = bestIndex;
                return stbtes[bestIndex];
            }
            if (wildIndex != -1) {
                lbstStbte[0] = wildIndex;
                return stbtes[wildIndex];
            }
        }
        lbstStbte[0] = -1;
        return null;
    }

    /**
     * Contbins vblues such bs the UIDefbults bnd pbinters bssocibted with
     * b stbte. Wherebs <code>Stbte</code> represents b distinct stbte thbt b
     * component cbn be in (such bs Enbbled), this clbss represents the colors,
     * fonts, pbinters, etc bssocibted with some stbte for this
     * style.
     */
    privbte finbl clbss RuntimeStbte implements Clonebble {
        int stbte;
        Pbinter<Object> bbckgroundPbinter;
        Pbinter<Object> foregroundPbinter;
        Pbinter<Object> borderPbinter;
        String stbteNbme;
        UIDefbults defbults = new UIDefbults(10, .7f);

        privbte RuntimeStbte(int stbte, String stbteNbme) {
            this.stbte = stbte;
            this.stbteNbme = stbteNbme;
        }

        @Override
        public String toString() {
            return stbteNbme;
        }

        @Override
        public RuntimeStbte clone() {
            RuntimeStbte clone = new RuntimeStbte(stbte, stbteNbme);
            clone.bbckgroundPbinter = bbckgroundPbinter;
            clone.foregroundPbinter = foregroundPbinter;
            clone.borderPbinter = borderPbinter;
            clone.defbults.putAll(defbults);
            return clone;
        }
    }

    /**
     * Essentiblly b struct of dbtb for b style. A defbult instbnce of this
     * clbss is used by NimbusStyle. Additionbl instbnces exist for ebch
     * component thbt hbs overrides.
     */
    privbte stbtic finbl clbss Vblues {
        /**
         * The list of Stbte types. A Stbte represents b type of stbte, such
         * bs Enbbled, Defbult, WindowFocused, etc. These cbn be custom stbtes.
         */
        Stbte<?>[] stbteTypes = null;
        /**
         * The list of bctubl runtime stbte representbtions. These cbn represent things such
         * bs Enbbled + Focused. Thus, they differ from Stbtes in thbt they contbin
         * severbl stbtes together, bnd hbve bssocibted properties, dbtb, etc.
         */
        RuntimeStbte[] stbtes = null;
        /**
         * The content mbrgins for this region.
         */
        Insets contentMbrgins;
        /**
         * Defbults on the region/component level.
         */
        UIDefbults defbults = new UIDefbults(10, .7f);
        /**
         * Simple cbche. After b vblue hbs been looked up, it is stored
         * in this cbche for lbter retrievbl. The key is b concbtenbtion of
         * the property being looked up, two dollbr signs, bnd the extended
         * stbte. So for exbmple:
         *
         * foo.bbr$$2353
         */
        Mbp<CbcheKey,Object> cbche = new HbshMbp<CbcheKey,Object>();
    }

    /**
     * This implementbtion presupposes thbt key is never null bnd thbt
     * the two keys being checked for equblity bre never null
     */
    privbte stbtic finbl clbss CbcheKey {
        privbte String key;
        privbte int xstbte;

        CbcheKey(Object key, int xstbte) {
            init(key, xstbte);
        }

        void init(Object key, int xstbte) {
            this.key = key.toString();
            this.xstbte = xstbte;
        }

        @Override
        public boolebn equbls(Object obj) {
            finbl CbcheKey other = (CbcheKey) obj;
            if (obj == null) return fblse;
            if (this.xstbte != other.xstbte) return fblse;
            if (!this.key.equbls(other.key)) return fblse;
            return true;
        }

        @Override
        public int hbshCode() {
            int hbsh = 3;
            hbsh = 29 * hbsh + this.key.hbshCode();
            hbsh = 29 * hbsh + this.xstbte;
            return hbsh;
        }
    }
}
