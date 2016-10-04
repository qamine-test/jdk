/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.nio.dibnnfls.FilfCibnnfl;
import sun.nio.di.FilfCibnnflImpl;


/**
 * A filf output strfbm is bn output strfbm for writing dbtb to b
 * <dodf>Filf</dodf> or to b <dodf>FilfDfsdriptor</dodf>. Wiftifr or not
 * b filf is bvbilbblf or mby bf drfbtfd dfpfnds upon tif undfrlying
 * plbtform.  Somf plbtforms, in pbrtidulbr, bllow b filf to bf opfnfd
 * for writing by only onf <tt>FilfOutputStrfbm</tt> (or otifr
 * filf-writing objfdt) bt b timf.  In sudi situbtions tif donstrudtors in
 * tiis dlbss will fbil if tif filf involvfd is blrfbdy opfn.
 *
 * <p><dodf>FilfOutputStrfbm</dodf> is mfbnt for writing strfbms of rbw bytfs
 * sudi bs imbgf dbtb. For writing strfbms of dibrbdtfrs, donsidfr using
 * <dodf>FilfWritfr</dodf>.
 *
 * @butior  Artiur vbn Hoff
 * @sff     jbvb.io.Filf
 * @sff     jbvb.io.FilfDfsdriptor
 * @sff     jbvb.io.FilfInputStrfbm
 * @sff     jbvb.nio.filf.Filfs#nfwOutputStrfbm
 * @sindf   1.0
 */
publid
dlbss FilfOutputStrfbm fxtfnds OutputStrfbm
{
    /**
     * Tif systfm dfpfndfnt filf dfsdriptor.
     */
    privbtf finbl FilfDfsdriptor fd;

    /**
     * Truf if tif filf is opfnfd for bppfnd.
     */
    privbtf finbl boolfbn bppfnd;

    /**
     * Tif bssodibtfd dibnnfl, initiblizfd lbzily.
     */
    privbtf FilfCibnnfl dibnnfl;

    /**
     * Tif pbti of tif rfffrfndfd filf
     * (null if tif strfbm is drfbtfd witi b filf dfsdriptor)
     */
    privbtf finbl String pbti;

    privbtf finbl Objfdt dlosfLodk = nfw Objfdt();
    privbtf volbtilf boolfbn dlosfd = fblsf;

    /**
     * Crfbtfs b filf output strfbm to writf to tif filf witi tif
     * spfdififd nbmf. A nfw <dodf>FilfDfsdriptor</dodf> objfdt is
     * drfbtfd to rfprfsfnt tiis filf donnfdtion.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkWritf</dodf>
     * mftiod is dbllfd witi <dodf>nbmf</dodf> bs its brgumfnt.
     * <p>
     * If tif filf fxists but is b dirfdtory rbtifr tibn b rfgulbr filf, dofs
     * not fxist but dbnnot bf drfbtfd, or dbnnot bf opfnfd for bny otifr
     * rfbson tifn b <dodf>FilfNotFoundExdfption</dodf> is tirown.
     *
     * @pbrbm      nbmf   tif systfm-dfpfndfnt filfnbmf
     * @fxdfption  FilfNotFoundExdfption  if tif filf fxists but is b dirfdtory
     *                   rbtifr tibn b rfgulbr filf, dofs not fxist but dbnnot
     *                   bf drfbtfd, or dbnnot bf opfnfd for bny otifr rfbson
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *               <dodf>difdkWritf</dodf> mftiod dfnifs writf bddfss
     *               to tif filf.
     * @sff        jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)
     */
    publid FilfOutputStrfbm(String nbmf) tirows FilfNotFoundExdfption {
        tiis(nbmf != null ? nfw Filf(nbmf) : null, fblsf);
    }

    /**
     * Crfbtfs b filf output strfbm to writf to tif filf witi tif spfdififd
     * nbmf.  If tif sfdond brgumfnt is <dodf>truf</dodf>, tifn
     * bytfs will bf writtfn to tif fnd of tif filf rbtifr tibn tif bfginning.
     * A nfw <dodf>FilfDfsdriptor</dodf> objfdt is drfbtfd to rfprfsfnt tiis
     * filf donnfdtion.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkWritf</dodf>
     * mftiod is dbllfd witi <dodf>nbmf</dodf> bs its brgumfnt.
     * <p>
     * If tif filf fxists but is b dirfdtory rbtifr tibn b rfgulbr filf, dofs
     * not fxist but dbnnot bf drfbtfd, or dbnnot bf opfnfd for bny otifr
     * rfbson tifn b <dodf>FilfNotFoundExdfption</dodf> is tirown.
     *
     * @pbrbm     nbmf        tif systfm-dfpfndfnt filf nbmf
     * @pbrbm     bppfnd      if <dodf>truf</dodf>, tifn bytfs will bf writtfn
     *                   to tif fnd of tif filf rbtifr tibn tif bfginning
     * @fxdfption  FilfNotFoundExdfption  if tif filf fxists but is b dirfdtory
     *                   rbtifr tibn b rfgulbr filf, dofs not fxist but dbnnot
     *                   bf drfbtfd, or dbnnot bf opfnfd for bny otifr rfbson.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *               <dodf>difdkWritf</dodf> mftiod dfnifs writf bddfss
     *               to tif filf.
     * @sff        jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)
     * @sindf     1.1
     */
    publid FilfOutputStrfbm(String nbmf, boolfbn bppfnd)
        tirows FilfNotFoundExdfption
    {
        tiis(nbmf != null ? nfw Filf(nbmf) : null, bppfnd);
    }

    /**
     * Crfbtfs b filf output strfbm to writf to tif filf rfprfsfntfd by
     * tif spfdififd <dodf>Filf</dodf> objfdt. A nfw
     * <dodf>FilfDfsdriptor</dodf> objfdt is drfbtfd to rfprfsfnt tiis
     * filf donnfdtion.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkWritf</dodf>
     * mftiod is dbllfd witi tif pbti rfprfsfntfd by tif <dodf>filf</dodf>
     * brgumfnt bs its brgumfnt.
     * <p>
     * If tif filf fxists but is b dirfdtory rbtifr tibn b rfgulbr filf, dofs
     * not fxist but dbnnot bf drfbtfd, or dbnnot bf opfnfd for bny otifr
     * rfbson tifn b <dodf>FilfNotFoundExdfption</dodf> is tirown.
     *
     * @pbrbm      filf               tif filf to bf opfnfd for writing.
     * @fxdfption  FilfNotFoundExdfption  if tif filf fxists but is b dirfdtory
     *                   rbtifr tibn b rfgulbr filf, dofs not fxist but dbnnot
     *                   bf drfbtfd, or dbnnot bf opfnfd for bny otifr rfbson
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *               <dodf>difdkWritf</dodf> mftiod dfnifs writf bddfss
     *               to tif filf.
     * @sff        jbvb.io.Filf#gftPbti()
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)
     */
    publid FilfOutputStrfbm(Filf filf) tirows FilfNotFoundExdfption {
        tiis(filf, fblsf);
    }

    /**
     * Crfbtfs b filf output strfbm to writf to tif filf rfprfsfntfd by
     * tif spfdififd <dodf>Filf</dodf> objfdt. If tif sfdond brgumfnt is
     * <dodf>truf</dodf>, tifn bytfs will bf writtfn to tif fnd of tif filf
     * rbtifr tibn tif bfginning. A nfw <dodf>FilfDfsdriptor</dodf> objfdt is
     * drfbtfd to rfprfsfnt tiis filf donnfdtion.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkWritf</dodf>
     * mftiod is dbllfd witi tif pbti rfprfsfntfd by tif <dodf>filf</dodf>
     * brgumfnt bs its brgumfnt.
     * <p>
     * If tif filf fxists but is b dirfdtory rbtifr tibn b rfgulbr filf, dofs
     * not fxist but dbnnot bf drfbtfd, or dbnnot bf opfnfd for bny otifr
     * rfbson tifn b <dodf>FilfNotFoundExdfption</dodf> is tirown.
     *
     * @pbrbm      filf               tif filf to bf opfnfd for writing.
     * @pbrbm     bppfnd      if <dodf>truf</dodf>, tifn bytfs will bf writtfn
     *                   to tif fnd of tif filf rbtifr tibn tif bfginning
     * @fxdfption  FilfNotFoundExdfption  if tif filf fxists but is b dirfdtory
     *                   rbtifr tibn b rfgulbr filf, dofs not fxist but dbnnot
     *                   bf drfbtfd, or dbnnot bf opfnfd for bny otifr rfbson
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *               <dodf>difdkWritf</dodf> mftiod dfnifs writf bddfss
     *               to tif filf.
     * @sff        jbvb.io.Filf#gftPbti()
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.lbng.String)
     * @sindf 1.4
     */
    publid FilfOutputStrfbm(Filf filf, boolfbn bppfnd)
        tirows FilfNotFoundExdfption
    {
        String nbmf = (filf != null ? filf.gftPbti() : null);
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkWritf(nbmf);
        }
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (filf.isInvblid()) {
            tirow nfw FilfNotFoundExdfption("Invblid filf pbti");
        }
        tiis.fd = nfw FilfDfsdriptor();
        fd.bttbdi(tiis);
        tiis.bppfnd = bppfnd;
        tiis.pbti = nbmf;

        opfn(nbmf, bppfnd);
    }

    /**
     * Crfbtfs b filf output strfbm to writf to tif spfdififd filf
     * dfsdriptor, wiidi rfprfsfnts bn fxisting donnfdtion to bn bdtubl
     * filf in tif filf systfm.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkWritf</dodf>
     * mftiod is dbllfd witi tif filf dfsdriptor <dodf>fdObj</dodf>
     * brgumfnt bs its brgumfnt.
     * <p>
     * If <dodf>fdObj</dodf> is null tifn b <dodf>NullPointfrExdfption</dodf>
     * is tirown.
     * <p>
     * Tiis donstrudtor dofs not tirow bn fxdfption if <dodf>fdObj</dodf>
     * is {@link jbvb.io.FilfDfsdriptor#vblid() invblid}.
     * Howfvfr, if tif mftiods brf invokfd on tif rfsulting strfbm to bttfmpt
     * I/O on tif strfbm, bn <dodf>IOExdfption</dodf> is tirown.
     *
     * @pbrbm      fdObj   tif filf dfsdriptor to bf opfnfd for writing
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *               <dodf>difdkWritf</dodf> mftiod dfnifs
     *               writf bddfss to tif filf dfsdriptor
     * @sff        jbvb.lbng.SfdurityMbnbgfr#difdkWritf(jbvb.io.FilfDfsdriptor)
     */
    publid FilfOutputStrfbm(FilfDfsdriptor fdObj) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (fdObj == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (sfdurity != null) {
            sfdurity.difdkWritf(fdObj);
        }
        tiis.fd = fdObj;
        tiis.bppfnd = fblsf;
        tiis.pbti = null;

        fd.bttbdi(tiis);
    }

    /**
     * Opfns b filf, witi tif spfdififd nbmf, for ovfrwriting or bppfnding.
     * @pbrbm nbmf nbmf of filf to bf opfnfd
     * @pbrbm bppfnd wiftifr tif filf is to bf opfnfd in bppfnd modf
     */
    privbtf nbtivf void opfn(String nbmf, boolfbn bppfnd)
        tirows FilfNotFoundExdfption;

    /**
     * Writfs tif spfdififd bytf to tiis filf output strfbm.
     *
     * @pbrbm   b   tif bytf to bf writtfn.
     * @pbrbm   bppfnd   {@dodf truf} if tif writf opfrbtion first
     *     bdvbndfs tif position to tif fnd of filf
     */
    privbtf nbtivf void writf(int b, boolfbn bppfnd) tirows IOExdfption;

    /**
     * Writfs tif spfdififd bytf to tiis filf output strfbm. Implfmfnts
     * tif <dodf>writf</dodf> mftiod of <dodf>OutputStrfbm</dodf>.
     *
     * @pbrbm      b   tif bytf to bf writtfn.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void writf(int b) tirows IOExdfption {
        writf(b, bppfnd);
    }

    /**
     * Writfs b sub brrby bs b sfqufndf of bytfs.
     * @pbrbm b tif dbtb to bf writtfn
     * @pbrbm off tif stbrt offsft in tif dbtb
     * @pbrbm lfn tif numbfr of bytfs tibt brf writtfn
     * @pbrbm bppfnd {@dodf truf} to first bdvbndf tif position to tif
     *     fnd of filf
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf nbtivf void writfBytfs(bytf b[], int off, int lfn, boolfbn bppfnd)
        tirows IOExdfption;

    /**
     * Writfs <dodf>b.lfngti</dodf> bytfs from tif spfdififd bytf brrby
     * to tiis filf output strfbm.
     *
     * @pbrbm      b   tif dbtb.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void writf(bytf b[]) tirows IOExdfption {
        writfBytfs(b, 0, b.lfngti, bppfnd);
    }

    /**
     * Writfs <dodf>lfn</dodf> bytfs from tif spfdififd bytf brrby
     * stbrting bt offsft <dodf>off</dodf> to tiis filf output strfbm.
     *
     * @pbrbm      b     tif dbtb.
     * @pbrbm      off   tif stbrt offsft in tif dbtb.
     * @pbrbm      lfn   tif numbfr of bytfs to writf.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        writfBytfs(b, off, lfn, bppfnd);
    }

    /**
     * Closfs tiis filf output strfbm bnd rflfbsfs bny systfm rfsourdfs
     * bssodibtfd witi tiis strfbm. Tiis filf output strfbm mby no longfr
     * bf usfd for writing bytfs.
     *
     * <p> If tiis strfbm ibs bn bssodibtfd dibnnfl tifn tif dibnnfl is dlosfd
     * bs wfll.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     *
     * @rfvisfd 1.4
     * @spfd JSR-51
     */
    publid void dlosf() tirows IOExdfption {
        syndironizfd (dlosfLodk) {
            if (dlosfd) {
                rfturn;
            }
            dlosfd = truf;
        }

        if (dibnnfl != null) {
            dibnnfl.dlosf();
        }

        fd.dlosfAll(nfw Closfbblf() {
            publid void dlosf() tirows IOExdfption {
               dlosf0();
           }
        });
    }

    /**
     * Rfturns tif filf dfsdriptor bssodibtfd witi tiis strfbm.
     *
     * @rfturn  tif <dodf>FilfDfsdriptor</dodf> objfdt tibt rfprfsfnts
     *          tif donnfdtion to tif filf in tif filf systfm bfing usfd
     *          by tiis <dodf>FilfOutputStrfbm</dodf> objfdt.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FilfDfsdriptor
     */
     publid finbl FilfDfsdriptor gftFD()  tirows IOExdfption {
        if (fd != null) {
            rfturn fd;
        }
        tirow nfw IOExdfption();
     }

    /**
     * Rfturns tif uniquf {@link jbvb.nio.dibnnfls.FilfCibnnfl FilfCibnnfl}
     * objfdt bssodibtfd witi tiis filf output strfbm.
     *
     * <p> Tif initibl {@link jbvb.nio.dibnnfls.FilfCibnnfl#position()
     * position} of tif rfturnfd dibnnfl will bf fqubl to tif
     * numbfr of bytfs writtfn to tif filf so fbr unlfss tiis strfbm is in
     * bppfnd modf, in wiidi dbsf it will bf fqubl to tif sizf of tif filf.
     * Writing bytfs to tiis strfbm will indrfmfnt tif dibnnfl's position
     * bddordingly.  Cibnging tif dibnnfl's position, fitifr fxpliditly or by
     * writing, will dibngf tiis strfbm's filf position.
     *
     * @rfturn  tif filf dibnnfl bssodibtfd witi tiis filf output strfbm
     *
     * @sindf 1.4
     * @spfd JSR-51
     */
    publid FilfCibnnfl gftCibnnfl() {
        syndironizfd (tiis) {
            if (dibnnfl == null) {
                dibnnfl = FilfCibnnflImpl.opfn(fd, pbti, fblsf, truf, bppfnd, tiis);
            }
            rfturn dibnnfl;
        }
    }

    /**
     * Clfbns up tif donnfdtion to tif filf, bnd fnsurfs tibt tif
     * <dodf>dlosf</dodf> mftiod of tiis filf output strfbm is
     * dbllfd wifn tifrf brf no morf rfffrfndfs to tiis strfbm.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     * @sff        jbvb.io.FilfInputStrfbm#dlosf()
     */
    protfdtfd void finblizf() tirows IOExdfption {
        if (fd != null) {
            if (fd == FilfDfsdriptor.out || fd == FilfDfsdriptor.frr) {
                flusi();
            } flsf {
                /* if fd is sibrfd, tif rfffrfndfs in FilfDfsdriptor
                 * will fnsurf tibt finblizfr is only dbllfd wifn
                 * sbff to do so. All rfffrfndfs using tif fd ibvf
                 * bfdomf unrfbdibblf. Wf dbn dbll dlosf()
                 */
                dlosf();
            }
        }
    }

    privbtf nbtivf void dlosf0() tirows IOExdfption;

    privbtf stbtid nbtivf void initIDs();

    stbtid {
        initIDs();
    }

}
