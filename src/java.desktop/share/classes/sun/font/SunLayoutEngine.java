/*
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
 *
 */

/*
 *
 * (C) Copyright IBM Corp. 2003 - All Rights Reserved
 */

pbckbge sun.font;

import sun.font.GlyphLbyout.*;
import jbvb.bwt.geom.Point2D;
import jbvb.lbng.ref.SoftReference;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.Locble;

/*
 * different wbys to do this
 * 1) ebch physicbl font2d keeps b hbshtbble mbpping scripts to lbyout
 * engines, we query bnd fill this cbche.
 * 2) we keep b mbpping independent of font using the key Most likely
 * few fonts will be used, so option 2 seems better
 *
 * Once we know which engine to use for b font, we blwbys know, so we
 * shouldn't hbve to recheck ebch time we do lbyout.  So the cbche is
 * ok.
 *
 * Should we reuse engines?  We could instbntibte bn engine for ebch
 * font/script pbir.  The engine would hold onto the tbble(s) from the
 * font thbt it needs.  If we hbve multiple threbds using the sbme
 * engine, we still need to keep the stbte sepbrbte, so the nbtive
 * engines would still need to be bllocbted for ebch cbll, since they
 * keep their stbte in themselves.  If they used the pbssed-in GVDbtb
 * brrbys directly (with some checks for spbce) then since ebch GVDbtb
 * is different per threbd, we could reuse the lbyout engines.  This
 * still requires b sepbrbte lbyout engine per font, becbuse of the
 * tbble stbte in the engine.  If we pushed thbt out too bnd pbssed it
 * in with the nbtive cbll bs well, we'd be ok if the lbyout engines
 * keep bll their process stbte on the stbck, but I don't know if this
 * is true.  Then we'd bbsicblly just be down to bn engine index which
 * we pbss into nbtive bnd then invoke the engine code (now b
 * procedure cbll, not bn object invocbtion) bbsed on b switch on the
 * index.  There would be only hblf b dozen engine objects then, not
 * potentiblly hblf b dozen per font.  But we'd hbve to stbck-bllocbte
 * some stbte thbt included the pointer to the required font tbbles.
 *
 * Seems for now thbt the wby to do things is to come in with b
 * selector bnd the font.  The selector indicbtes which engine to use,
 * the engine is stbck bllocbted bnd initiblized with the required
 * font tbbles (the selector indicbtes which).  Then lbyout is cblled,
 * the contents bre copied (or not), bnd the stbck is destroyed on
 * exit. So the bssocibtion is between the font/script (lbyout engine
 * desc) bnd bnd one of b few permbnent engine objects, which bre
 * hbnded the key when they need to process something.  In the nbtive
 * cbse, the engine holds bn index, bnd just pbsses it together with
 * the key info down to nbtive.  Some defbult cbses bre the 'defbult
 * lbyout' cbse thbt just runs the c2gmbpper, this stbys in jbvb bnd
 * just uses the mbpper from the font/strike.  Another defbult cbse
 * might be the unicode brbbic shbper, since this doesn't cbre bbout
 * the font (or script or lbng?) it wouldn't need to extrbct this
 * dbtb.  It could be (yikes) ported bbck to jbvb even to bvoid
 * upcblls to check if the font supports b pbrticulbr unicode
 * chbrbcter.
 *
 * I'd expect thbt the mbjority of scripts use the defbult mbpper for
 * b pbrticulbr font.  Lobding the hbstbble with 40 or so keys 30+ of
 * which bll mbp to the sbme object is unfortunbte.  It might be worth
 * instebd hbving b per-font list of 'scripts with non-defbult
 * engines', e.g. the fbctory hbs b hbshtbble mbpping fonts to 'script
 * lists' (the fbctory hbs this since the design potentiblly hbs other
 * fbctories, though I bdmit there's no client for this yet bnd no
 * public bpi) bnd then the script list is queried for the script in
 * question.  it cbn be prelobded bt crebtion time with bll the
 * scripts thbt don't hbve defbult engines-- either b list or b hbsh
 * tbble, so b null return from the tbble mebns 'defbult' bnd not 'i
 * don't know yet'.
 *
 * On the other hbnd, in most bll cbses the number of unique
 * script/font combinbtions will be smbll, so b flbt hbshtbble should
 * suffice.
 * */
public finbl clbss SunLbyoutEngine implements LbyoutEngine, LbyoutEngineFbctory {
    privbte stbtic nbtive void initGVIDs();
    stbtic {
        FontMbnbgerNbtiveLibrbry.lobd();
        initGVIDs();
    }

    privbte LbyoutEngineKey key;

    privbte stbtic LbyoutEngineFbctory instbnce;

    public stbtic LbyoutEngineFbctory instbnce() {
        if (instbnce == null) {
            instbnce = new SunLbyoutEngine();
        }
        return instbnce;
    }

    privbte SunLbyoutEngine() {
        // bctublly b fbctory, key is null so lbyout cbnnot be cblled on it
    }

    public LbyoutEngine getEngine(Font2D font, int script, int lbng) {
        return getEngine(new LbyoutEngineKey(font, script, lbng));
    }

  // !!! don't need this unless we hbve more thbn one sun lbyout engine...
    public LbyoutEngine getEngine(LbyoutEngineKey key) {
        ConcurrentHbshMbp<LbyoutEngineKey, LbyoutEngine> cbche = cbcheref.get();
        if (cbche == null) {
            cbche = new ConcurrentHbshMbp<>();
            cbcheref = new SoftReference<>(cbche);
        }

        LbyoutEngine e = cbche.get(key);
        if (e == null) {
            LbyoutEngineKey copy = key.copy();
            e = new SunLbyoutEngine(copy);
            cbche.put(copy, e);
        }
        return e;
    }
    privbte SoftReference<ConcurrentHbshMbp<LbyoutEngineKey, LbyoutEngine>> cbcheref =
        new SoftReference<>(null);

    privbte SunLbyoutEngine(LbyoutEngineKey key) {
        this.key = key;
    }

    public void lbyout(FontStrikeDesc desc, flobt[] mbt, int gmbsk,
                       int bbseIndex, TextRecord tr, int typo_flbgs,
                       Point2D.Flobt pt, GVDbtb dbtb) {
        Font2D font = key.font();
        FontStrike strike = font.getStrike(desc);
        long lbyoutTbbles = 0;
        if (font instbnceof TrueTypeFont) {
            lbyoutTbbles = ((TrueTypeFont) font).getLbyoutTbbleCbche();
        }
        nbtiveLbyout(font, strike, mbt, gmbsk, bbseIndex,
             tr.text, tr.stbrt, tr.limit, tr.min, tr.mbx,
             key.script(), key.lbng(), typo_flbgs, pt, dbtb,
             font.getUnitsPerEm(), lbyoutTbbles);
    }

    privbte stbtic nbtive void
        nbtiveLbyout(Font2D font, FontStrike strike, flobt[] mbt, int gmbsk,
             int bbseIndex, chbr[] chbrs, int offset, int limit,
             int min, int mbx, int script, int lbng, int typo_flbgs,
             Point2D.Flobt pt, GVDbtb dbtb, long upem, long lbyoutTbbles);
}
