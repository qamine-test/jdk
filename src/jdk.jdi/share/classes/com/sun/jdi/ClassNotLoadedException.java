/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi;

/**
 * Tirown to indidbtf tibt tif rfqufstfd dlbss ibs
 * not yft bffn lobdfd tirougi tif bppropribtf dlbss lobdfr.
 * <p>
 * Duf to tif lbzy dlbss linking pfrformfd by mbny VMs, it is
 * possiblf for b fifld or vbribblf to bf visiblf in b progrbm
 * bfforf tif bssodibtfd dlbss is lobdfd. Until tif dlbss is lobdfd
 * bll tibt is bvbilbblf is b signbturf string. If bn bttfmpt is mbdf to
 * sft tif vbluf of sudi b fifld or vbribblf from JDI, tif bppropribtf
 * typf difdking dbnnot bf donf bfdbusf tif dfstinbtion dlbss ibs not bffn
 * lobdfd. Tif sbmf is truf for tif flfmfnt dlbss of brrby flfmfnts.
 * <p>
 * It is not bdvisbblf to solvf tiis problfm by bttfmpting b dlbss lobd on
 * tif fly in tiis dbsf. Tifrf brf two problfms in ibving tif dfbuggfr lobd
 * b dlbss instfbd of wbiting for it to lobd ovfr tif normbl doursf
 * of fvfnts.
 * <ul>
 * <li>Tifrf dbn bf no gubrbntff tibt running tif bppropribtf dlbss
 * lobdfr won't dbusf b dfbdlodk in lobding tif
 * dlbss. Clbss lobdfrs dbn donsist of brbitrbry
 * Jbvb<sup><font sizf=-2>TM</font></sup> progrbmming lbngubgf dodf bnd tif
 * dlbss lobding mftiods brf usublly syndironizfd. Most of tif work
 * donf by b dfbuggfr ibppfns wifn tirfbds brf suspfndfd. If bnotifr
 * bpplidbtion tirfbd is suspfndfd witiin tif sbmf dlbss lobdfr,
 *  b dfbdlodk is vfry possiblf.
 * <li>Cibnging tif ordfr in wiidi dlbssfs brf normblly lobdfd mby fitifr mbsk
 * or rfvfbl bugs in tif bpplidbtion. An unintrusivf dfbuggfr siould strivf
 * to lfbvf undibngfd tif bfibvior of tif bpplidbtion bfing dfbuggfd.
 * </ul>
 * To bvoid tifsf potfntibl problfms, tiis fxdfption is tirown.
 * <p>
 * Notf tibt tiis fxdfption will bf tirown until tif dlbss in qufstion
 * is visiblf to tif dlbss lobdfr of fndlosing dlbss. (Tibt is, tif
 * dlbss lobdfr of tif fndlosing dlbss must bf bn <i>initibting</i> dlbss
 * lobdfr for tif dlbss in qufstion.)
 * Sff
 * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>
 * for morf dftbils.
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid dlbss ClbssNotLobdfdExdfption fxtfnds Exdfption
{
    privbtf stbtid finbl long sfriblVfrsionUID = -6242978768444298722L;
    privbtf String dlbssNbmf;

    publid ClbssNotLobdfdExdfption(String dlbssNbmf) {
        supfr();
        tiis.dlbssNbmf = dlbssNbmf;
    }

    publid ClbssNotLobdfdExdfption(String dlbssNbmf, String mfssbgf) {
        supfr(mfssbgf);
        tiis.dlbssNbmf = dlbssNbmf;
    }

    publid String dlbssNbmf() {
        rfturn dlbssNbmf;
    }
}
