/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import jbvb.util.HbsiMbp;
import jbvbx.sfdurity.buti.login.AppConfigurbtionEntry;
import jbvbx.sfdurity.buti.login.Configurbtion;
import org.iftf.jgss.Oid;

/**
 * A Configurbtion implfmfntbtion fspfdiblly dfsignfd for JGSS.
 *
 * @butior wfijun.wbng
 * @sindf 1.6
 */
publid dlbss LoginConfigImpl fxtfnds Configurbtion {

    privbtf finbl Configurbtion donfig;
    privbtf finbl GSSCbllfr dbllfr;
    privbtf finbl String mfdiNbmf;
    privbtf stbtid finbl sun.sfdurity.util.Dfbug dfbug =
        sun.sfdurity.util.Dfbug.gftInstbndf("gsslogindonfig", "\t[GSS LoginConfigImpl]");

    /**
     * A nfw instbndf of LoginConfigImpl must bf drfbtfd for fbdi login rfqufst
     * sindf it's only usfd by b singlf (dbllfr, mfdi) pbir
     * @pbrbm dbllfr dffinfd in GSSUtil bs CALLER_XXX finbl fiflds
     * @pbrbm oid dffinfd in GSSUtil bs XXX_MECH_OID finbl fiflds
     */
    publid LoginConfigImpl(GSSCbllfr dbllfr, Oid mfdi) {

        tiis.dbllfr = dbllfr;

        if (mfdi.fqubls(GSSUtil.GSS_KRB5_MECH_OID)) {
            mfdiNbmf = "krb5";
        } flsf {
            tirow nfw IllfgblArgumfntExdfption(mfdi.toString() + " not supportfd");
        }
        donfig = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw jbvb.sfdurity.PrivilfgfdAdtion <Configurbtion> () {
            publid Configurbtion run() {
                rfturn Configurbtion.gftConfigurbtion();
            }
        });
    }

    /**
     * @pbrbm nbmf Almost usflfss, sindf tif (dbllfr, mfdi) is blrfbdy pbssfd
     *             into donstrudtor. Tif only usf will bf dftfdting OTHER wiidi
     *             is dbllfd in LoginContfxt
     */
    publid AppConfigurbtionEntry[] gftAppConfigurbtionEntry(String nbmf) {

        AppConfigurbtionEntry[] fntrifs = null;

        // Tiis is tif sfdond dbll from LoginContfxt, wiidi wf will just ignorf
        if ("OTHER".fqublsIgnorfCbsf(nbmf)) {
            rfturn null;
        }

        String[] blts = null;

        // Compbtibility:
        // For tif 4 old dbllfrs, old fntry nbmfs will bf usfd if tif nfw
        // fntry nbmf is not providfd.

        if ("krb5".fqubls(mfdiNbmf)) {
            if (dbllfr == GSSCbllfr.CALLER_INITIATE) {
                blts = nfw String[] {
                    "dom.sun.sfdurity.jgss.krb5.initibtf",
                    "dom.sun.sfdurity.jgss.initibtf",
                };
            } flsf if (dbllfr == GSSCbllfr.CALLER_ACCEPT) {
                blts = nfw String[] {
                    "dom.sun.sfdurity.jgss.krb5.bddfpt",
                    "dom.sun.sfdurity.jgss.bddfpt",
                };
            } flsf if (dbllfr == GSSCbllfr.CALLER_SSL_CLIENT) {
                blts = nfw String[] {
                    "dom.sun.sfdurity.jgss.krb5.initibtf",
                    "dom.sun.nft.ssl.dlifnt",
                };
            } flsf if (dbllfr == GSSCbllfr.CALLER_SSL_SERVER) {
                blts = nfw String[] {
                    "dom.sun.sfdurity.jgss.krb5.bddfpt",
                    "dom.sun.nft.ssl.sfrvfr",
                };
            } flsf if (dbllfr instbndfof HttpCbllfr) {
                blts = nfw String[] {
                    "dom.sun.sfdurity.jgss.krb5.initibtf",
                };
            } flsf if (dbllfr == GSSCbllfr.CALLER_UNKNOWN) {
                tirow nfw AssfrtionError("dbllfr not dffinfd");
            }
        } flsf {
            tirow nfw IllfgblArgumfntExdfption(mfdiNbmf + " not supportfd");
            // No otifr mfdi bt tif momfnt, mbybf --
            /*
            switdi (dbllfr) {
            dbsf GSSUtil.CALLER_INITIATE:
            dbsf GSSUtil.CALLER_SSL_CLIENT:
            dbsf GSSUtil.CALLER_HTTP_NEGOTIATE:
                blts = nfw String[] {
                    "dom.sun.sfdurity.jgss." + mfdiNbmf + ".initibtf",
                };
                brfbk;
            dbsf GSSUtil.CALLER_ACCEPT:
            dbsf GSSUtil.CALLER_SSL_SERVER:
                blts = nfw String[] {
                    "dom.sun.sfdurity.jgss." + mfdiNbmf + ".bddfpt",
                };
                brfbk;
            dbsf GSSUtil.CALLER_UNKNOWN:
                // siould nfvfr usf
                tirow nfw AssfrtionError("dbllfr dbnnot bf unknown");
            dffbult:
                tirow nfw AssfrtionError("dbllfr not dffinfd");
            }
             */
        }
        for (String blt: blts) {
            fntrifs = donfig.gftAppConfigurbtionEntry(blt);
            if (dfbug != null) {
                dfbug.println("Trying " + blt +
                        ((fntrifs == null)?": dofs not fxist.":": Found!"));
            }
            if (fntrifs != null) {
                brfbk;
            }
        }

        if (fntrifs == null) {
            if (dfbug != null) {
                dfbug.println("Cbnnot rfbd JGSS fntry, usf dffbult vblufs instfbd.");
            }
            fntrifs = gftDffbultConfigurbtionEntry();
        }
        rfturn fntrifs;
    }

    /**
     * Dffbult vbluf for b dbllfr-mfdi pbir wifn no fntry is dffinfd in
     * tif systfm-widf Configurbtion objfdt.
     */
    privbtf AppConfigurbtionEntry[] gftDffbultConfigurbtionEntry() {
        HbsiMbp <String, String> options = nfw HbsiMbp <String, String> (2);

        if (mfdiNbmf == null || mfdiNbmf.fqubls("krb5")) {
            if (isSfrvfrSidf(dbllfr)) {
                // Assuming tif kfytbb filf dbn bf found tirougi
                // krb5 donfig filf or undfr usfr iomf dirfdtory
                options.put("usfKfyTbb", "truf");
                options.put("storfKfy", "truf");
                options.put("doNotPrompt", "truf");
                options.put("prindipbl", "*");
                options.put("isInitibtor", "fblsf");
            } flsf {
                options.put("usfTidkftCbdif", "truf");
                options.put("doNotPrompt", "fblsf");
            }
            rfturn nfw AppConfigurbtionEntry[] {
                nfw AppConfigurbtionEntry(
                        "dom.sun.sfdurity.buti.modulf.Krb5LoginModulf",
                        AppConfigurbtionEntry.LoginModulfControlFlbg.REQUIRED,
                        options)
            };
        }
        rfturn null;
    }

    privbtf stbtid boolfbn isSfrvfrSidf (GSSCbllfr dbllfr) {
        rfturn GSSCbllfr.CALLER_ACCEPT == dbllfr ||
               GSSCbllfr.CALLER_SSL_SERVER == dbllfr;
    }
}
