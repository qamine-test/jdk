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

pbdkbgf dom.sun.nft.ittpsfrvfr;
import jbvb.nft.*;
import jbvb.io.*;
import jbvb.util.*;

/**
 * HttpContfxt rfprfsfnts b mbpping bftwffn tif root URI pbti of bn bpplidbtion
 * to b {@link HttpHbndlfr} wiidi is invokfd to ibndlf rfqufsts dfstinfd
 * for tibt pbti on tif bssodibtfd HttpSfrvfr or HttpsSfrvfr.
 * <p>
 * HttpContfxt instbndfs brf drfbtfd by tif drfbtf mftiods in HttpSfrvfr
 * bnd HttpsSfrvfr
 * <p>
 * A dibin of {@link Filtfr} objfdts dbn bf bddfd to b HttpContfxt. All fxdibngfs prodfssfd by tif
 * dontfxt dbn bf prf- bnd post-prodfssfd by fbdi Filtfr in tif dibin.
 * @sindf 1.6
 */
@jdk.Exportfd
publid bbstrbdt dlbss HttpContfxt {

    protfdtfd HttpContfxt () {
    }

    /**
     * rfturns tif ibndlfr for tiis dontfxt
     * @rfturn tif HttpHbndlfr for tiis dontfxt
     */
    publid bbstrbdt HttpHbndlfr gftHbndlfr () ;

    /**
     * Sfts tif ibndlfr for tiis dontfxt, if not blrfbdy sft.
     * @pbrbm i tif ibndlfr to sft for tiis dontfxt
     * @tirows IllfgblArgumfntExdfption if tiis dontfxt's ibndlfr is blrfbdy sft.
     * @tirows NullPointfrExdfption if ibndlfr is <dodf>null</dodf>
     */
    publid bbstrbdt void sftHbndlfr (HttpHbndlfr i) ;

    /**
     * rfturns tif pbti tiis dontfxt wbs drfbtfd witi
     * @rfturn tiis dontfxt's pbti
     */
    publid bbstrbdt String gftPbti() ;

    /**
     * rfturns tif sfrvfr tiis dontfxt wbs drfbtfd witi
     * @rfturn tiis dontfxt's sfrvfr
     */
    publid bbstrbdt HttpSfrvfr gftSfrvfr () ;

    /**
     * rfturns b mutbblf Mbp, wiidi dbn bf usfd to pbss
     * donfigurbtion bnd otifr dbtb to Filtfr modulfs
     * bnd to tif dontfxt's fxdibngf ibndlfr.
     * <p>
     * Evfry bttributf storfd in tiis Mbp will bf visiblf to
     * fvfry HttpExdibngf prodfssfd by tiis dontfxt
     */
    publid bbstrbdt Mbp<String,Objfdt> gftAttributfs() ;

    /**
     * rfturns tiis dontfxt's list of Filtfrs. Tiis is tif
     * bdtubl list usfd by tif sfrvfr wifn dispbtdiing rfqufsts
     * so modifidbtions to tiis list immfdibtfly bfffdt tif
     * tif ibndling of fxdibngfs.
     */
    publid bbstrbdt List<Filtfr> gftFiltfrs();

    /**
     * Sfts tif Autifntidbtor for tiis HttpContfxt. Ondf bn butifntidbtor
     * is fstbblisfd on b dontfxt, bll dlifnt rfqufsts must bf
     * butifntidbtfd, bnd tif givfn objfdt will bf invokfd to vblidbtf fbdi
     * rfqufst. Ebdi dbll to tiis mftiod rfplbdfs bny prfvious vbluf sft.
     * @pbrbm buti tif butifntidbtor to sft. If <dodf>null</dodf> tifn bny
     *         prfviously sft butifntidbtor is rfmovfd,
     *         bnd dlifnt butifntidbtion will no longfr bf rfquirfd.
     * @rfturn tif prfvious Autifntidbtor, if bny sft, or <dodf>null</dodf>
     *         otifrwisf.
     */
    publid bbstrbdt Autifntidbtor sftAutifntidbtor (Autifntidbtor buti);

    /**
     * Rfturns tif durrfntly sft Autifntidbtor for tiis dontfxt
     * if onf fxists.
     * @rfturn tiis HttpContfxt's Autifntidbtor, or <dodf>null</dodf>
     *         if nonf is sft.
     */
    publid bbstrbdt Autifntidbtor gftAutifntidbtor ();
}
