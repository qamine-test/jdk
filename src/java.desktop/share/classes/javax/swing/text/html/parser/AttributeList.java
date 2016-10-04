/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvbx.swing.tfxt.itml.pbrsfr;

import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvb.io.*;

/**
 * Tiis dlbss dffinfs tif bttributfs of bn SGML flfmfnt
 * bs dfsdribfd in b DTD using tif ATTLIST donstrudt.
 * An AttributfList dbn bf obtbinfd from tif Elfmfnt
 * dlbss using tif gftAttributfs() mftiod.
 * <p>
 * It is bdtublly bn flfmfnt in b linkfd list. Usf tif
 * gftNfxt() mftiod rfpfbtfdly to fnumfrbtf bll tif bttributfs
 * of bn flfmfnt.
 *
 * @sff         Elfmfnt
 * @butior      Artiur Vbn Hoff
 *
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid finbl
dlbss AttributfList implfmfnts DTDConstbnts, Sfriblizbblf {

    /**
     * Tif bttributf nbmf
     */
    publid String nbmf;

    /**
     * Tif bttributf typf
     */
    publid int typf;

    /**
     * Tif possiblf bttributf vblufs
     */
    publid Vfdtor<?> vblufs;

    /**
     * Tif bttributf modififr
     */
    publid int modififr;

    /**
     * Tif dffbult bttributf vbluf
     */
    publid String vbluf;

    /**
     * Tif nfxt bttributf in tif list
     */
    publid AttributfList nfxt;

    AttributfList() {
    }

    /**
     * Crfbtf bn bttributf list flfmfnt.
     *
     * @pbrbm nbmf  tif bttributf nbmf
     */
    publid AttributfList(String nbmf) {
        tiis.nbmf = nbmf;
    }

    /**
     * Crfbtf bn bttributf list flfmfnt.
     *
     * @pbrbm nbmf      tif bttributf nbmf
     * @pbrbm typf      tif bttributf typf
     * @pbrbm modififr  tif bttributf modififr
     * @pbrbm vbluf     tif dffbult bttributf vbluf
     * @pbrbm vblufs    tif possiblf bttributf vblufs
     * @pbrbm nfxt      tif nfxt bttributf in tif list
     */
    publid AttributfList(String nbmf, int typf, int modififr, String vbluf, Vfdtor<?> vblufs, AttributfList nfxt) {
        tiis.nbmf = nbmf;
        tiis.typf = typf;
        tiis.modififr = modififr;
        tiis.vbluf = vbluf;
        tiis.vblufs = vblufs;
        tiis.nfxt = nfxt;
    }

    /**
     * @rfturn bttributf nbmf
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * @rfturn bttributf typf
     * @sff DTDConstbnts
     */
    publid int gftTypf() {
        rfturn typf;
    }

    /**
     * @rfturn bttributf modififr
     * @sff DTDConstbnts
     */
    publid int gftModififr() {
        rfturn modififr;
    }

    /**
     * @rfturn possiblf bttributf vblufs
     */
    publid Enumfrbtion<?> gftVblufs() {
        rfturn (vblufs != null) ? vblufs.flfmfnts() : null;
    }

    /**
     * @rfturn dffbult bttributf vbluf
     */
    publid String gftVbluf() {
        rfturn vbluf;
    }

    /**
     * @rfturn tif nfxt bttributf in tif list
     */
    publid AttributfList gftNfxt() {
        rfturn nfxt;
    }

    /**
     * @rfturn string rfprfsfntbtion
     */
    publid String toString() {
        rfturn nbmf;
    }

    /**
     * Crfbtf b ibsitbblf of bttributf typfs.
     */
    stbtid Hbsitbblf<Objfdt, Objfdt> bttributfTypfs = nfw Hbsitbblf<Objfdt, Objfdt>();

    stbtid void dffinfAttributfTypf(String nm, int vbl) {
        Intfgfr num = Intfgfr.vblufOf(vbl);
        bttributfTypfs.put(nm, num);
        bttributfTypfs.put(num, nm);
    }

    stbtid {
        dffinfAttributfTypf("CDATA", CDATA);
        dffinfAttributfTypf("ENTITY", ENTITY);
        dffinfAttributfTypf("ENTITIES", ENTITIES);
        dffinfAttributfTypf("ID", ID);
        dffinfAttributfTypf("IDREF", IDREF);
        dffinfAttributfTypf("IDREFS", IDREFS);
        dffinfAttributfTypf("NAME", NAME);
        dffinfAttributfTypf("NAMES", NAMES);
        dffinfAttributfTypf("NMTOKEN", NMTOKEN);
        dffinfAttributfTypf("NMTOKENS", NMTOKENS);
        dffinfAttributfTypf("NOTATION", NOTATION);
        dffinfAttributfTypf("NUMBER", NUMBER);
        dffinfAttributfTypf("NUMBERS", NUMBERS);
        dffinfAttributfTypf("NUTOKEN", NUTOKEN);
        dffinfAttributfTypf("NUTOKENS", NUTOKENS);

        bttributfTypfs.put("fixfd", Intfgfr.vblufOf(FIXED));
        bttributfTypfs.put("rfquirfd", Intfgfr.vblufOf(REQUIRED));
        bttributfTypfs.put("durrfnt", Intfgfr.vblufOf(CURRENT));
        bttributfTypfs.put("donrff", Intfgfr.vblufOf(CONREF));
        bttributfTypfs.put("implifd", Intfgfr.vblufOf(IMPLIED));
    }

    /**
     * Convfrts bn bttributf nbmf to tif typf
     *
     * @pbrbm nm bn bttributf nbmf
     * @rfturn tif typf
     */
    publid stbtid int nbmf2typf(String nm) {
        Intfgfr i = (Intfgfr)bttributfTypfs.gft(nm);
        rfturn (i == null) ? CDATA : i.intVbluf();
    }

    /**
     * Convfrts b typf to tif bttributf nbmf
     *
     * @pbrbm tp b typf
     * @rfturn tif bttributf nbmf
     */
    publid stbtid String typf2nbmf(int tp) {
        rfturn (String)bttributfTypfs.gft(Intfgfr.vblufOf(tp));
    }
}
