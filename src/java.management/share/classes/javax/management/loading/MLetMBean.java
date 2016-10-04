/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.lobding;

import jbvb.nft.URL;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Sft;
import jbvb.util.Enumfrbtion;

import jbvbx.mbnbgfmfnt.*;



/**
 * Exposfs tif rfmotf mbnbgfmfnt intfrfbdf of tif MLft
 * MBfbn.
 *
 * @sindf 1.5
 */
publid intfrfbdf MLftMBfbn   {


    /**
     * Lobds b tfxt filf dontbining MLET tbgs tibt dffinf tif MBfbns
     * to bf bddfd to tif MBfbn sfrvfr. Tif lodbtion of tif tfxt filf is
     * spfdififd by b URL. Tif tfxt filf is rfbd using tif UTF-8
     * fndoding. Tif MBfbns spfdififd in tif MLET filf will bf
     * instbntibtfd bnd rfgistfrfd in tif MBfbn sfrvfr.
     *
     * @pbrbm url Tif URL of tif tfxt filf to bf lobdfd bs String objfdt.
     *
     * @rfturn A sft dontbining onf fntry pfr MLET tbg in tif m-lft
     * tfxt filf lobdfd.  Ebdi fntry spfdififs fitifr tif
     * ObjfdtInstbndf for tif drfbtfd MBfbn, or b tirowbblf objfdt
     * (tibt is, bn frror or bn fxdfption) if tif MBfbn dould not bf
     * drfbtfd.
     *
     * @fxdfption SfrvidfNotFoundExdfption Onf of tif following frrors
     * ibs oddurrfd: Tif m-lft tfxt filf dofs not dontbin bn MLET tbg,
     * tif m-lft tfxt filf is not found, b mbndbtory bttributf of tif
     * MLET tbg is not spfdififd, tif vbluf of url is mblformfd.
     */
    publid Sft<Objfdt> gftMBfbnsFromURL(String url)
            tirows SfrvidfNotFoundExdfption;

    /**
     * Lobds b tfxt filf dontbining MLET tbgs tibt dffinf tif MBfbns
     * to bf bddfd to tif MBfbn sfrvfr. Tif lodbtion of tif tfxt filf is
     * spfdififd by b URL. Tif tfxt filf is rfbd using tif UTF-8
     * fndoding. Tif MBfbns spfdififd in tif MLET filf will bf
     * instbntibtfd bnd rfgistfrfd in tif MBfbn sfrvfr.
     *
     * @pbrbm url Tif URL of tif tfxt filf to bf lobdfd bs URL objfdt.
     *
     * @rfturn A sft dontbining onf fntry pfr MLET tbg in tif m-lft
     * tfxt filf lobdfd.  Ebdi fntry spfdififs fitifr tif
     * ObjfdtInstbndf for tif drfbtfd MBfbn, or b tirowbblf objfdt
     * (tibt is, bn frror or bn fxdfption) if tif MBfbn dould not bf
     * drfbtfd.
     *
     * @fxdfption SfrvidfNotFoundExdfption Onf of tif following frrors
     * ibs oddurrfd: Tif m-lft tfxt filf dofs not dontbin bn MLET tbg,
     * tif m-lft tfxt filf is not found, b mbndbtory bttributf of tif
     * MLET tbg is not spfdififd, tif vbluf of url is null.
     */
    publid Sft<Objfdt> gftMBfbnsFromURL(URL url)
            tirows SfrvidfNotFoundExdfption;

    /**
     * Appfnds tif spfdififd URL to tif list of URLs to sfbrdi for dlbssfs bnd
     * rfsourdfs.
     *
     * @pbrbm url tif URL to bdd.
     */
    publid void bddURL(URL url) ;

    /**
     * Appfnds tif spfdififd URL to tif list of URLs to sfbrdi for dlbssfs bnd
     * rfsourdfs.
     *
     * @pbrbm url tif URL to bdd.
     *
     * @fxdfption SfrvidfNotFoundExdfption Tif spfdififd URL is mblformfd.
     */
    publid void bddURL(String url) tirows SfrvidfNotFoundExdfption;

    /**
     * Rfturns tif sfbrdi pbti of URLs for lobding dlbssfs bnd rfsourdfs.
     * Tiis indludfs tif originbl list of URLs spfdififd to tif donstrudtor,
     * blong witi bny URLs subsfqufntly bppfndfd by tif bddURL() mftiod.
     *
     * @rfturn tif list of URLs.
     */
    publid URL[] gftURLs();

    /** Finds tif rfsourdf witi tif givfn nbmf.
     * A rfsourdf is somf dbtb (imbgfs, budio, tfxt, ftd) tibt dbn bf bddfssfd by dlbss dodf in b wby tibt is
     *   indfpfndfnt of tif lodbtion of tif dodf.
     *   Tif nbmf of b rfsourdf is b "/"-sfpbrbtfd pbti nbmf tibt idfntififs tif rfsourdf.
     *
     * @pbrbm nbmf Tif rfsourdf nbmf
     *
     * @rfturn  An URL for rfbding tif rfsourdf, or null if tif rfsourdf dould not bf found or tif dbllfr dofsn't ibvf bdfqubtf privilfgfs to gft tif
     * rfsourdf.
     */
    publid URL gftRfsourdf(String nbmf);

    /** Rfturns bn input strfbm for rfbding tif spfdififd rfsourdf. Tif sfbrdi ordfr is dfsdribfd in tif dodumfntbtion for
     *  gftRfsourdf(String).
     *
     * @pbrbm nbmf  Tif rfsourdf nbmf
     *
     * @rfturn An input strfbm for rfbding tif rfsourdf, or null if tif rfsourdf dould not bf found
     *
     */
    publid InputStrfbm gftRfsourdfAsStrfbm(String nbmf);

    /**
     * Finds bll tif rfsourdfs witi tif givfn nbmf. A rfsourdf is somf
     * dbtb (imbgfs, budio, tfxt, ftd) tibt dbn bf bddfssfd by dlbss
     * dodf in b wby tibt is indfpfndfnt of tif lodbtion of tif dodf.
     * Tif nbmf of b rfsourdf is b "/"-sfpbrbtfd pbti nbmf tibt
     * idfntififs tif rfsourdf.
     *
     * @pbrbm nbmf Tif  rfsourdf nbmf.
     *
     * @rfturn An fnumfrbtion of URL to tif rfsourdf. If no rfsourdfs
     * dould bf found, tif fnumfrbtion will bf fmpty. Rfsourdfs tibt
     * dbnnot bf bddfssfd will not bf in tif fnumfrbtion.
     *
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs wifn
     * sfbrdiing for rfsourdfs.
     */
    publid Enumfrbtion<URL> gftRfsourdfs(String nbmf) tirows IOExdfption;

    /**
     * Gfts tif durrfnt dirfdtory usfd by tif librbry lobdfr for
     * storing nbtivf librbrifs bfforf tify brf lobdfd into mfmory.
     *
     * @rfturn Tif durrfnt dirfdtory usfd by tif librbry lobdfr.
     *
     * @sff #sftLibrbryDirfdtory
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis implfmfntbtion
     * dofs not support storing nbtivf librbrifs in tiis wby.
     */
    publid String gftLibrbryDirfdtory();

    /**
     * Sfts tif dirfdtory usfd by tif librbry lobdfr for storing
     * nbtivf librbrifs bfforf tify brf lobdfd into mfmory.
     *
     * @pbrbm libdir Tif dirfdtory usfd by tif librbry lobdfr.
     *
     * @sff #gftLibrbryDirfdtory
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tiis implfmfntbtion
     * dofs not support storing nbtivf librbrifs in tiis wby.
     */
    publid void sftLibrbryDirfdtory(String libdir);

 }
