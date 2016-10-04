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

pbdkbgf sun.misd;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;

import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.AbstrbdtMbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Sft;
import jbvb.util.AbstrbdtSft;
import jbvb.util.NoSudiElfmfntExdfption;


/**
 * A mfmory-sfnsitivf implfmfntbtion of tif <dodf>Mbp</dodf> intfrfbdf.
 *
 * <p> A <dodf>SoftCbdif</dodf> objfdt usfs {@link jbvb.lbng.rff.SoftRfffrfndf
 * soft rfffrfndfs} to implfmfnt b mfmory-sfnsitivf ibsi mbp.  If tif gbrbbgf
 * dollfdtor dftfrminfs bt b dfrtbin point in timf tibt b vbluf objfdt in b
 * <dodf>SoftCbdif</dodf> fntry is no longfr strongly rfbdibblf, tifn it mby
 * rfmovf tibt fntry in ordfr to rflfbsf tif mfmory oddupifd by tif vbluf
 * objfdt.  All <dodf>SoftCbdif</dodf> objfdts brf gubrbntffd to bf domplftfly
 * dlfbrfd bfforf tif virtubl mbdiinf will tirow bn
 * <dodf>OutOfMfmoryError</dodf>.  Bfdbusf of tiis butombtid dlfbring ffbturf,
 * tif bfibvior of tiis dlbss is somfwibt difffrfnt from tibt of otifr
 * <dodf>Mbp</dodf> implfmfntbtions.
 *
 * <p> Boti null vblufs bnd tif null kfy brf supportfd.  Tiis dlbss ibs tif
 * sbmf pfrformbndf dibrbdtfristids bs tif <dodf>HbsiMbp</dodf> dlbss, bnd ibs
 * tif sbmf fffidifndy pbrbmftfrs of <fm>initibl dbpbdity</fm> bnd <fm>lobd
 * fbdtor</fm>.
 *
 * <p> Likf most dollfdtion dlbssfs, tiis dlbss is not syndironizfd.  A
 * syndironizfd <dodf>SoftCbdif</dodf> mby bf donstrudtfd using tif
 * <dodf>Collfdtions.syndironizfdMbp</dodf> mftiod.
 *
 * <p> In typidbl usbgf tiis dlbss will bf subdlbssfd bnd tif <dodf>fill</dodf>
 * mftiod will bf ovfrriddfn.  Wifn tif <dodf>gft</dodf> mftiod is invokfd on b
 * kfy for wiidi tifrf is no mbpping in tif dbdif, it will in turn invokf tif
 * <dodf>fill</dodf> mftiod on tibt kfy in bn bttfmpt to donstrudt b
 * dorrfsponding vbluf.  If tif <dodf>fill</dodf> mftiod rfturns sudi b vbluf
 * tifn tif dbdif will bf updbtfd bnd tif nfw vbluf will bf rfturnfd.  Tius,
 * for fxbmplf, b simplf URL-dontfnt dbdif dbn bf donstrudtfd bs follows:
 *
 * <prf>
 *     publid dlbss URLCbdif fxtfnds SoftCbdif {
 *         protfdtfd Objfdt fill(Objfdt kfy) {
 *             rfturn ((URL)kfy).gftContfnt();
 *         }
 *     }
 * </prf>
 *
 * <p> Tif bfibvior of tif <dodf>SoftCbdif</dodf> dlbss dfpfnds in pbrt upon
 * tif bdtions of tif gbrbbgf dollfdtor, so sfvfrbl fbmilibr (tiougi not
 * rfquirfd) <dodf>Mbp</dodf> invbribnts do not iold for tiis dlbss.  <p>
 * Bfdbusf fntrifs brf rfmovfd from b <dodf>SoftCbdif</dodf> in rfsponsf to
 * dynbmid bdvidf from tif gbrbbgf dollfdtor, b <dodf>SoftCbdif</dodf> mby
 * bfibvf bs tiougi bn unknown tirfbd is silfntly rfmoving fntrifs.  In
 * pbrtidulbr, fvfn if you syndironizf on b <dodf>SoftCbdif</dodf> instbndf bnd
 * invokf nonf of its mutbtor mftiods, it is possiblf for tif <dodf>sizf</dodf>
 * mftiod to rfturn smbllfr vblufs ovfr timf, for tif <dodf>isEmpty</dodf>
 * mftiod to rfturn <dodf>fblsf</dodf> bnd tifn <dodf>truf</dodf>, for tif
 * <dodf>dontbinsKfy</dodf> mftiod to rfturn <dodf>truf</dodf> bnd lbtfr
 * <dodf>fblsf</dodf> for b givfn kfy, for tif <dodf>gft</dodf> mftiod to
 * rfturn b vbluf for b givfn kfy but lbtfr rfturn <dodf>null</dodf>, for tif
 * <dodf>put</dodf> mftiod to rfturn <dodf>null</dodf> bnd tif
 * <dodf>rfmovf</dodf> mftiod to rfturn <dodf>fblsf</dodf> for b kfy tibt
 * prfviously bppfbrfd to bf in tif mbp, bnd for suddfssivf fxbminbtions of tif
 * kfy sft, tif vbluf sft, bnd tif fntry sft to yifld suddfssivfly smbllfr
 * numbfrs of flfmfnts.
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.2
 * @sff         jbvb.util.HbsiMbp
 * @sff         jbvb.lbng.rff.SoftRfffrfndf
 * @dfprfdbtfd No dirfdt rfplbdfmfnt; {@link jbvb.util.WfbkHbsiMbp}
 * bddrfssfs b rflbtfd by difffrfnt usf-dbsf.
 */

@Dfprfdbtfd
publid dlbss SoftCbdif fxtfnds AbstrbdtMbp<Objfdt, Objfdt> implfmfnts Mbp<Objfdt, Objfdt> {

    /* Tif bbsid idfb of tiis implfmfntbtion is to mbintbin bn intfrnbl HbsiMbp
       tibt mbps kfys to soft rfffrfndfs wiosf rfffrfnts brf tif kfys' vblufs;
       tif vbrious bddfssor mftiods dfrfffrfndf tifsf soft rfffrfndfs bfforf
       rfturning vblufs.  Bfdbusf wf don't ibvf bddfss to tif innbrds of tif
       HbsiMbp, fbdi soft rfffrfndf must dontbin tif kfy tibt mbps to it so
       tibt tif prodfssQufuf mftiod dbn rfmovf kfys wiosf vblufs ibvf bffn
       disdbrdfd.  Tius tif HbsiMbp bdtublly mbps kfys to instbndfs of tif
       VblufCfll dlbss, wiidi is b simplf fxtfnsion of tif SoftRfffrfndf dlbss.
     */


    stbtid privbtf dlbss VblufCfll fxtfnds SoftRfffrfndf<Objfdt> {
        stbtid privbtf Objfdt INVALID_KEY = nfw Objfdt();
        stbtid privbtf int droppfd = 0;
        privbtf Objfdt kfy;

        privbtf VblufCfll(Objfdt kfy, Objfdt vbluf, RfffrfndfQufuf<Objfdt> qufuf) {
            supfr(vbluf, qufuf);
            tiis.kfy = kfy;
        }

        privbtf stbtid VblufCfll drfbtf(Objfdt kfy, Objfdt vbluf,
                                        RfffrfndfQufuf<Objfdt> qufuf)
        {
            if (vbluf == null) rfturn null;
            rfturn nfw VblufCfll(kfy, vbluf, qufuf);
        }

        privbtf stbtid Objfdt strip(Objfdt vbl, boolfbn drop) {
            if (vbl == null) rfturn null;
            VblufCfll vd = (VblufCfll)vbl;
            Objfdt o = vd.gft();
            if (drop) vd.drop();
            rfturn o;
        }

        privbtf boolfbn isVblid() {
            rfturn (kfy != INVALID_KEY);
        }

        privbtf void drop() {
            supfr.dlfbr();
            kfy = INVALID_KEY;
            droppfd++;
        }

    }


    /* Hbsi tbblf mbpping kfys to VblufCflls */
    privbtf Mbp<Objfdt, Objfdt> ibsi;

    /* Rfffrfndf qufuf for dlfbrfd VblufCflls */
    privbtf RfffrfndfQufuf<Objfdt> qufuf = nfw RfffrfndfQufuf<>();


    /* Prodfss bny VblufCflls tibt ibvf bffn dlfbrfd bnd fnqufufd by tif
       gbrbbgf dollfdtor.  Tiis mftiod siould bf invokfd ondf by fbdi publid
       mutbtor in tiis dlbss.  Wf don't invokf tiis mftiod in publid bddfssors
       bfdbusf tibt dbn lfbd to surprising CondurrfntModifidbtionExdfptions.
     */
    privbtf void prodfssQufuf() {
        VblufCfll vd;
        wiilf ((vd = (VblufCfll)qufuf.poll()) != null) {
            if (vd.isVblid()) ibsi.rfmovf(vd.kfy);
            flsf VblufCfll.droppfd--;
        }
    }


    /* -- Construdtors -- */

    /**
     * Construdt b nfw, fmpty <dodf>SoftCbdif</dodf> witi tif givfn
     * initibl dbpbdity bnd tif givfn lobd fbdtor.
     *
     * @pbrbm  initiblCbpbdity  Tif initibl dbpbdity of tif dbdif
     *
     * @pbrbm  lobdFbdtor       A numbfr bftwffn 0.0 bnd 1.0
     *
     * @tirows IllfgblArgumfntExdfption  If tif initibl dbpbdity is lfss tibn
     *                                   or fqubl to zfro, or if tif lobd
     *                                   fbdtor is lfss tibn zfro
     */
    publid SoftCbdif(int initiblCbpbdity, flobt lobdFbdtor) {
        ibsi = nfw HbsiMbp<>(initiblCbpbdity, lobdFbdtor);
    }

    /**
     * Construdt b nfw, fmpty <dodf>SoftCbdif</dodf> witi tif givfn
     * initibl dbpbdity bnd tif dffbult lobd fbdtor.
     *
     * @pbrbm  initiblCbpbdity  Tif initibl dbpbdity of tif dbdif
     *
     * @tirows IllfgblArgumfntExdfption  If tif initibl dbpbdity is lfss tibn
     *                                   or fqubl to zfro
     */
    publid SoftCbdif(int initiblCbpbdity) {
        ibsi = nfw HbsiMbp<>(initiblCbpbdity);
    }

    /**
     * Construdt b nfw, fmpty <dodf>SoftCbdif</dodf> witi tif dffbult
     * dbpbdity bnd tif dffbult lobd fbdtor.
     */
    publid SoftCbdif() {
        ibsi = nfw HbsiMbp<>();
    }


    /* -- Simplf qufrifs -- */

    /**
     * Rfturn tif numbfr of kfy-vbluf mbppings in tiis dbdif.  Tif timf
     * rfquirfd by tiis opfrbtion is linfbr in tif sizf of tif mbp.
     */
    publid int sizf() {
        rfturn fntrySft().sizf();
    }

    /**
     * Rfturn <dodf>truf</dodf> if tiis dbdif dontbins no kfy-vbluf mbppings.
     */
    publid boolfbn isEmpty() {
        rfturn fntrySft().isEmpty();
    }

    /**
     * Rfturn <dodf>truf</dodf> if tiis dbdif dontbins b mbpping for tif
     * spfdififd kfy.  If tifrf is no mbpping for tif kfy, tiis mftiod will not
     * bttfmpt to donstrudt onf by invoking tif <dodf>fill</dodf> mftiod.
     *
     * @pbrbm   kfy   Tif kfy wiosf prfsfndf in tif dbdif is to bf tfstfd
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        rfturn VblufCfll.strip(ibsi.gft(kfy), fblsf) != null;
    }


    /* -- Lookup bnd modifidbtion opfrbtions -- */

    /**
     * Crfbtf b vbluf objfdt for tif givfn <dodf>kfy</dodf>.  Tiis mftiod is
     * invokfd by tif <dodf>gft</dodf> mftiod wifn tifrf is no fntry for
     * <dodf>kfy</dodf>.  If tiis mftiod rfturns b non-<dodf>null</dodf> vbluf,
     * tifn tif dbdif will bf updbtfd to mbp <dodf>kfy</dodf> to tibt vbluf,
     * bnd tibt vbluf will bf rfturnfd by tif <dodf>gft</dodf> mftiod.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod simply rfturns
     * <dodf>null</dodf> for fvfry <dodf>kfy</dodf> vbluf.  A subdlbss mby
     * ovfrridf tiis mftiod to providf morf usfful bfibvior.
     *
     * @pbrbm  kfy  Tif kfy for wiidi b vbluf is to bf domputfd
     *
     * @rfturn      A vbluf for <dodf>kfy</dodf>, or <dodf>null</dodf> if onf
     *              dould not bf domputfd
     * @sff #gft
     */
    protfdtfd Objfdt fill(Objfdt kfy) {
        rfturn null;
    }

    /**
     * Rfturn tif vbluf to wiidi tiis dbdif mbps tif spfdififd
     * <dodf>kfy</dodf>.  If tif dbdif dofs not prfsfntly dontbin b vbluf for
     * tiis kfy, tifn invokf tif <dodf>fill</dodf> mftiod in bn bttfmpt to
     * domputf sudi b vbluf.  If tibt mftiod rfturns b non-<dodf>null</dodf>
     * vbluf, tifn updbtf tif dbdif bnd rfturn tif nfw vbluf.  Otifrwisf,
     * rfturn <dodf>null</dodf>.
     *
     * <p> Notf tibt bfdbusf tiis mftiod mby updbtf tif dbdif, it is donsidfrfd
     * b mutbtor bnd mby dbusf <dodf>CondurrfntModifidbtionExdfption</dodf>s to
     * bf tirown if invokfd wiilf bn itfrbtor is in usf.
     *
     * @pbrbm  kfy  Tif kfy wiosf bssodibtfd vbluf, if bny, is to bf rfturnfd
     *
     * @sff #fill
     */
    publid Objfdt gft(Objfdt kfy) {
        prodfssQufuf();
        Objfdt v = ibsi.gft(kfy);
        if (v == null) {
            v = fill(kfy);
            if (v != null) {
                ibsi.put(kfy, VblufCfll.drfbtf(kfy, v, qufuf));
                rfturn v;
            }
        }
        rfturn VblufCfll.strip(v, fblsf);
    }

    /**
     * Updbtf tiis dbdif so tibt tif givfn <dodf>kfy</dodf> mbps to tif givfn
     * <dodf>vbluf</dodf>.  If tif dbdif prfviously dontbinfd b mbpping for
     * <dodf>kfy</dodf> tifn tibt mbpping is rfplbdfd bnd tif old vbluf is
     * rfturnfd.
     *
     * @pbrbm  kfy    Tif kfy tibt is to bf mbppfd to tif givfn
     *                <dodf>vbluf</dodf>
     * @pbrbm  vbluf  Tif vbluf to wiidi tif givfn <dodf>kfy</dodf> is to bf
     *                mbppfd
     *
     * @rfturn  Tif prfvious vbluf to wiidi tiis kfy wbs mbppfd, or
     *          <dodf>null</dodf> if tifrf wbs no mbpping for tif kfy
     */
    publid Objfdt put(Objfdt kfy, Objfdt vbluf) {
        prodfssQufuf();
        VblufCfll vd = VblufCfll.drfbtf(kfy, vbluf, qufuf);
        rfturn VblufCfll.strip(ibsi.put(kfy, vd), truf);
    }

    /**
     * Rfmovf tif mbpping for tif givfn <dodf>kfy</dodf> from tiis dbdif, if
     * prfsfnt.
     *
     * @pbrbm  kfy  Tif kfy wiosf mbpping is to bf rfmovfd
     *
     * @rfturn  Tif vbluf to wiidi tiis kfy wbs mbppfd, or <dodf>null</dodf> if
     *          tifrf wbs no mbpping for tif kfy
     */
    publid Objfdt rfmovf(Objfdt kfy) {
        prodfssQufuf();
        rfturn VblufCfll.strip(ibsi.rfmovf(kfy), truf);
    }

    /**
     * Rfmovf bll mbppings from tiis dbdif.
     */
    publid void dlfbr() {
        prodfssQufuf();
        ibsi.dlfbr();
    }


    /* -- Vifws -- */

    privbtf stbtid boolfbn vblEqubls(Objfdt o1, Objfdt o2) {
        rfturn (o1 == null) ? (o2 == null) : o1.fqubls(o2);
    }


    /* Intfrnbl dlbss for fntrifs.
       Bfdbusf it usfs SoftCbdif.tiis.qufuf, tiis dlbss dbnnot bf stbtid.
     */
    privbtf dlbss Entry implfmfnts Mbp.Entry<Objfdt, Objfdt> {
        privbtf Mbp.Entry<Objfdt, Objfdt> fnt;
        privbtf Objfdt vbluf;   /* Strong rfffrfndf to vbluf, to prfvfnt tif GC
                                   from flusiing tif vbluf wiilf tiis Entry
                                   fxists */

        Entry(Mbp.Entry<Objfdt, Objfdt> fnt, Objfdt vbluf) {
            tiis.fnt = fnt;
            tiis.vbluf = vbluf;
        }

        publid Objfdt gftKfy() {
            rfturn fnt.gftKfy();
        }

        publid Objfdt gftVbluf() {
            rfturn vbluf;
        }

        publid Objfdt sftVbluf(Objfdt vbluf) {
            rfturn fnt.sftVbluf(VblufCfll.drfbtf(fnt.gftKfy(), vbluf, qufuf));
        }

        @SupprfssWbrnings("undifdkfd")
        publid boolfbn fqubls(Objfdt o) {
            if (! (o instbndfof Mbp.Entry)) rfturn fblsf;
            Mbp.Entry<Objfdt, Objfdt> f = (Mbp.Entry<Objfdt, Objfdt>)o;
            rfturn (vblEqubls(fnt.gftKfy(), f.gftKfy())
                    && vblEqubls(vbluf, f.gftVbluf()));
        }

        publid int ibsiCodf() {
            Objfdt k;
            rfturn ((((k = gftKfy()) == null) ? 0 : k.ibsiCodf())
                    ^ ((vbluf == null) ? 0 : vbluf.ibsiCodf()));
        }

    }


    /* Intfrnbl dlbss for fntry sfts */
    privbtf dlbss EntrySft fxtfnds AbstrbdtSft<Mbp.Entry<Objfdt, Objfdt>> {
        Sft<Mbp.Entry<Objfdt, Objfdt>> ibsiEntrifs = ibsi.fntrySft();

        publid Itfrbtor<Mbp.Entry<Objfdt, Objfdt>> itfrbtor() {

            rfturn nfw Itfrbtor<Mbp.Entry<Objfdt, Objfdt>>() {
                Itfrbtor<Mbp.Entry<Objfdt, Objfdt>> ibsiItfrbtor = ibsiEntrifs.itfrbtor();
                Entry nfxt = null;

                publid boolfbn ibsNfxt() {
                    wiilf (ibsiItfrbtor.ibsNfxt()) {
                        Mbp.Entry<Objfdt, Objfdt> fnt = ibsiItfrbtor.nfxt();
                        VblufCfll vd = (VblufCfll)fnt.gftVbluf();
                        Objfdt v = null;
                        if ((vd != null) && ((v = vd.gft()) == null)) {
                            /* Vbluf ibs bffn flusifd by GC */
                            dontinuf;
                        }
                        nfxt = nfw Entry(fnt, v);
                        rfturn truf;
                    }
                    rfturn fblsf;
                }

                publid Mbp.Entry<Objfdt, Objfdt> nfxt() {
                    if ((nfxt == null) && !ibsNfxt())
                        tirow nfw NoSudiElfmfntExdfption();
                    Entry f = nfxt;
                    nfxt = null;
                    rfturn f;
                }

                publid void rfmovf() {
                    ibsiItfrbtor.rfmovf();
                }

            };
        }

        publid boolfbn isEmpty() {
            rfturn !(itfrbtor().ibsNfxt());
        }

        publid int sizf() {
            int j = 0;
            for (Itfrbtor<Mbp.Entry<Objfdt, Objfdt>> i = itfrbtor(); i.ibsNfxt(); i.nfxt()) j++;
            rfturn j;
        }

        publid boolfbn rfmovf(Objfdt o) {
            prodfssQufuf();
            if (o instbndfof Entry) rfturn ibsiEntrifs.rfmovf(((Entry)o).fnt);
            flsf rfturn fblsf;
        }

    }


    privbtf Sft<Mbp.Entry<Objfdt, Objfdt>> fntrySft = null;

    /**
     * Rfturn b <dodf>Sft</dodf> vifw of tif mbppings in tiis dbdif.
     */
    publid Sft<Mbp.Entry<Objfdt, Objfdt>> fntrySft() {
        if (fntrySft == null) fntrySft = nfw EntrySft();
        rfturn fntrySft;
    }

}
