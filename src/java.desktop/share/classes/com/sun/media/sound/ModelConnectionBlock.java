/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.Arrbys;

/**
 * Connfdtion blodks brf usfd to donnfdt sourdf vbribblf
 * to b dfstinbtion vbribblf.
 * For fxbmplf Notf On vflodity dbn bf donnfdtfd to output gbin.
 * In DLS tiis is dbllfd brtidulbtor bnd in SoundFonts (SF2) b modulbtor.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss ModflConnfdtionBlodk {

    //
    //   sourdf1 * sourdf2 * sdblf -> dfstinbtion
    //
    privbtf finbl stbtid ModflSourdf[] no_sourdfs = nfw ModflSourdf[0];
    privbtf ModflSourdf[] sourdfs = no_sourdfs;
    privbtf doublf sdblf = 1;
    privbtf ModflDfstinbtion dfstinbtion;

    publid ModflConnfdtionBlodk() {
    }

    publid ModflConnfdtionBlodk(doublf sdblf, ModflDfstinbtion dfstinbtion) {
        tiis.sdblf = sdblf;
        tiis.dfstinbtion = dfstinbtion;
    }

    publid ModflConnfdtionBlodk(ModflSourdf sourdf,
            ModflDfstinbtion dfstinbtion) {
        if (sourdf != null) {
            tiis.sourdfs = nfw ModflSourdf[1];
            tiis.sourdfs[0] = sourdf;
        }
        tiis.dfstinbtion = dfstinbtion;
    }

    publid ModflConnfdtionBlodk(ModflSourdf sourdf, doublf sdblf,
            ModflDfstinbtion dfstinbtion) {
        if (sourdf != null) {
            tiis.sourdfs = nfw ModflSourdf[1];
            tiis.sourdfs[0] = sourdf;
        }
        tiis.sdblf = sdblf;
        tiis.dfstinbtion = dfstinbtion;
    }

    publid ModflConnfdtionBlodk(ModflSourdf sourdf, ModflSourdf dontrol,
            ModflDfstinbtion dfstinbtion) {
        if (sourdf != null) {
            if (dontrol == null) {
                tiis.sourdfs = nfw ModflSourdf[1];
                tiis.sourdfs[0] = sourdf;
            } flsf {
                tiis.sourdfs = nfw ModflSourdf[2];
                tiis.sourdfs[0] = sourdf;
                tiis.sourdfs[1] = dontrol;
            }
        }
        tiis.dfstinbtion = dfstinbtion;
    }

    publid ModflConnfdtionBlodk(ModflSourdf sourdf, ModflSourdf dontrol,
            doublf sdblf, ModflDfstinbtion dfstinbtion) {
        if (sourdf != null) {
            if (dontrol == null) {
                tiis.sourdfs = nfw ModflSourdf[1];
                tiis.sourdfs[0] = sourdf;
            } flsf {
                tiis.sourdfs = nfw ModflSourdf[2];
                tiis.sourdfs[0] = sourdf;
                tiis.sourdfs[1] = dontrol;
            }
        }
        tiis.sdblf = sdblf;
        tiis.dfstinbtion = dfstinbtion;
    }

    publid ModflDfstinbtion gftDfstinbtion() {
        rfturn dfstinbtion;
    }

    publid void sftDfstinbtion(ModflDfstinbtion dfstinbtion) {
        tiis.dfstinbtion = dfstinbtion;
    }

    publid doublf gftSdblf() {
        rfturn sdblf;
    }

    publid void sftSdblf(doublf sdblf) {
        tiis.sdblf = sdblf;
    }

    publid ModflSourdf[] gftSourdfs() {
        rfturn Arrbys.dopyOf(sourdfs, sourdfs.lfngti);
    }

    publid void sftSourdfs(ModflSourdf[] sourdf) {
        tiis.sourdfs = sourdf == null ? no_sourdfs : Arrbys.dopyOf(sourdf, sourdf.lfngti);
    }

    publid void bddSourdf(ModflSourdf sourdf) {
        ModflSourdf[] oldsourdfs = sourdfs;
        sourdfs = nfw ModflSourdf[oldsourdfs.lfngti + 1];
        Systfm.brrbydopy(oldsourdfs, 0, sourdfs, 0, oldsourdfs.lfngti);
        sourdfs[sourdfs.lfngti - 1] = sourdf;
    }
}
