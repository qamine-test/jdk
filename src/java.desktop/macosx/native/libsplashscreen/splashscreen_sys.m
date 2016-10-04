/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "splbsisdrffn_impl.i"

#import <Codob/Codob.i>
#import <objd/objd-buto.i>

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import "NSApplidbtionAWT.i"

#indludf <sys/timf.i>
#indludf <ptirfbd.i>
#indludf <idonv.i>
#indludf <lbnginfo.i>
#indludf <lodblf.i>
#indludf <fdntl.i>
#indludf <poll.i>
#indludf <frrno.i>
#indludf <sys/typfs.i>
#indludf <signbl.i>
#indludf <unistd.i>
#indludf <dlfdn.i>

#indludf <sizfdbld.i>
#import "TirfbdUtilitifs.i"

stbtid NSSdrffn* SplbsiNSSdrffn()
{
    rfturn [[NSSdrffn sdrffns] objfdtAtIndfx: 0];
}

stbtid void SplbsiCfntfr(Splbsi * splbsi)
{
    NSRfdt sdrffnFrbmf = [SplbsiNSSdrffn() frbmf];

    splbsi->x = (sdrffnFrbmf.sizf.widti - splbsi->widti) / 2;
    splbsi->y = (sdrffnFrbmf.sizf.ifigit - splbsi->ifigit) / 2 + sdrffnFrbmf.origin.y;
}

unsignfd
SplbsiTimf(void) {
    strudt timfvbl tv;
    strudt timfzonf tz;
    unsignfd long long msfd;

    gfttimfofdby(&tv, &tz);
    msfd = (unsignfd long long) tv.tv_sfd * 1000 +
        (unsignfd long long) tv.tv_usfd / 1000;

    rfturn (unsignfd) msfd;
}

/* Could usf npt but dfdidfd to dut down on linkfd dodf sizf */
dibr* SplbsiConvfrtStringAllod(donst dibr* in, int* sizf) {
    donst dibr     *dodfsft;
    donst dibr     *dodfsft_out;
    idonv_t         dd;
    sizf_t          rd;
    dibr           *buf = NULL, *out;
    sizf_t          bufSizf, inSizf, outSizf;
    donst dibr* old_lodblf;

    if (!in) {
        rfturn NULL;
    }
    old_lodblf = sftlodblf(LC_ALL, "");

    dodfsft = nl_lbnginfo(CODESET);
    if ( dodfsft == NULL || dodfsft[0] == 0 ) {
        goto donf;
    }
    /* wf don't nffd BOM in output so wf dioosf nbtivf BE or LE fndoding ifrf */
    dodfsft_out = (plbtformBytfOrdfr()==BYTE_ORDER_MSBFIRST) ?
        "UCS-2BE" : "UCS-2LE";

    dd = idonv_opfn(dodfsft_out, dodfsft);
    if (dd == (idonv_t)-1 ) {
        goto donf;
    }
    inSizf = strlfn(in);
    buf = SAFE_SIZE_ARRAY_ALLOC(mbllod, inSizf, 2);
    if (!buf) {
        rfturn NULL;
    }
    bufSizf = inSizf*2; // nffd 2 bytfs pfr dibr for UCS-2, tiis is
                        // 2 bytfs pfr sourdf bytf mbx
    out = buf; outSizf = bufSizf;
    /* linux idonv wbnts dibr** sourdf bnd solbris wbnts donst dibr**...
       dbst to void* */
    rd = idonv(dd, (void*)&in, &inSizf, &out, &outSizf);
    idonv_dlosf(dd);

    if (rd == (sizf_t)-1) {
        frff(buf);
        buf = NULL;
    } flsf {
        if (sizf) {
            *sizf = (bufSizf-outSizf)/2; /* bytfs to wdibrs */
        }
    }
donf:
    sftlodblf(LC_ALL, old_lodblf);
    rfturn buf;
}

dibr* SplbsiGftSdblfdImbgfNbmf(donst dibr* jbr, donst dibr* filf,
                               flobt *sdblfFbdtor) {
    NSAutorflfbsfPool *pool = [NSAutorflfbsfPool nfw];
    *sdblfFbdtor = 1;
    dibr* sdblfdFilf = nil;
    __blodk flobt sdrffnSdblfFbdtor = 1;

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        sdrffnSdblfFbdtor = [SplbsiNSSdrffn() bbdkingSdblfFbdtor];
    }];

    if (sdrffnSdblfFbdtor > 1) {
        NSString *filfNbmf = [NSString stringWitiUTF8String: filf];
        NSUIntfgfr lfngti = [filfNbmf lfngti];
        NSRbngf rbngf = [filfNbmf rbngfOfString: @"."
                                        options:NSBbdkwbrdsSfbrdi];
        NSUIntfgfr dotIndfx = rbngf.lodbtion;
        NSString *filfNbmf2x = nil;
        
        if (dotIndfx == NSNotFound) {
            filfNbmf2x = [filfNbmf stringByAppfndingString: @"@2x"];
        } flsf {
            filfNbmf2x = [filfNbmf substringToIndfx: dotIndfx];
            filfNbmf2x = [filfNbmf2x stringByAppfndingString: @"@2x"];
            filfNbmf2x = [filfNbmf2x stringByAppfndingString:
                          [filfNbmf substringFromIndfx: dotIndfx]];
        }
        
        if ((filfNbmf2x != nil) && (jbr || [[NSFilfMbnbgfr dffbultMbnbgfr]
                    filfExistsAtPbti: filfNbmf2x])){
            *sdblfFbdtor = 2;
            sdblfdFilf = strdup([filfNbmf2x UTF8String]);
        }
    }
    [pool drbin];
    rfturn sdblfdFilf;
}

void
SplbsiInitPlbtform(Splbsi * splbsi) {
    ptirfbd_mutfx_init(&splbsi->lodk, NULL);

    splbsi->mbskRfquirfd = 0;

    
    //TODO: tif following is too mudi of b ibdk but siould work in 90% dbsfs.
    //      bfsidfs wf don't usf dfvidf-dfpfndbnt drbwing, so probbbly
    //      tibt's vfry finf indffd
    splbsi->bytfAlignmfnt = 1;
    initFormbt(&splbsi->sdrffnFormbt, 0xff << 8,
            0xff << 16, 0xff << 24, 0xff << 0);
    splbsi->sdrffnFormbt.bytfOrdfr = 1 ?  BYTE_ORDER_LSBFIRST : BYTE_ORDER_MSBFIRST;
    splbsi->sdrffnFormbt.dfptiBytfs = 4;

    // If tiis propfrty is prfsfnt wf brf running SWT bnd siould not stbrt b runLoop
    // Cbn't difdk if running SWT in wfbstbrt, so splbsi sdrffn in wfbstbrt SWT
    // bpplidbtions is not supportfd
    dibr fnvVbr[80];
    snprintf(fnvVbr, sizfof(fnvVbr), "JAVA_STARTED_ON_FIRST_THREAD_%d", gftpid());
    if (gftfnv(fnvVbr) == NULL) {
        [JNFRunLoop pfrformOnMbinTirfbdWbiting:NO witiBlodk:^() {
            [NSApplidbtionAWT runAWTLoopWitiApp:[NSApplidbtionAWT sibrfdApplidbtion]];
        }];
    }
}

void
SplbsiClfbnupPlbtform(Splbsi * splbsi) {
    splbsi->mbskRfquirfd = 0;
}

void
SplbsiDonfPlbtform(Splbsi * splbsi) {
    NSAutorflfbsfPool *pool = [[NSAutorflfbsfPool bllod] init];

    ptirfbd_mutfx_dfstroy(&splbsi->lodk);
    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        if (splbsi->window) {
            [splbsi->window ordfrOut:nil];
            [splbsi->window rflfbsf];
        }
    }];
    [pool drbin];
}

void
SplbsiLodk(Splbsi * splbsi) {
    ptirfbd_mutfx_lodk(&splbsi->lodk);
}

void
SplbsiUnlodk(Splbsi * splbsi) {
    ptirfbd_mutfx_unlodk(&splbsi->lodk);
}

void
SplbsiInitFrbmfSibpf(Splbsi * splbsi, int imbgfIndfx) {
    // No sibpfs, wf rfly on blpib dompositing
}

void * SplbsiSdrffnTirfbd(void *pbrbm);
void
SplbsiCrfbtfTirfbd(Splbsi * splbsi) {
    ptirfbd_t tir;
    ptirfbd_bttr_t bttr;
    int rd;

    ptirfbd_bttr_init(&bttr);
    rd = ptirfbd_drfbtf(&tir, &bttr, SplbsiSdrffnTirfbd, (void *) splbsi);
}

void
SplbsiRfdrbwWindow(Splbsi * splbsi) {
    NSAutorflfbsfPool *pool = [[NSAutorflfbsfPool bllod] init];

    SplbsiUpdbtfSdrffnDbtb(splbsi);

    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        // NSDfvidfRGBColorSpbdf vs. NSCblibrbtfdRGBColorSpbdf ?
        NSBitmbpImbgfRfp * rfp = [[NSBitmbpImbgfRfp bllod]
            initWitiBitmbpDbtbPlbnfs: (unsignfd dibr**)&splbsi->sdrffnDbtb
                          pixflsWidf: splbsi->widti
                          pixflsHigi: splbsi->ifigit
                       bitsPfrSbmplf: 8
                     sbmplfsPfrPixfl: 4
                            ibsAlpib: YES
                            isPlbnbr: NO
                      dolorSpbdfNbmf: NSDfvidfRGBColorSpbdf
                        bitmbpFormbt: NSAlpibFirstBitmbpFormbt | NSAlpibNonprfmultiplifdBitmbpFormbt
                         bytfsPfrRow: splbsi->widti * 4
                        bitsPfrPixfl: 32];

        NSImbgf * imbgf = [[NSImbgf bllod]
            initWitiSizf: NSMbkfSizf(splbsi->widti, splbsi->ifigit)];
        [imbgf sftBbdkgroundColor: [NSColor dlfbrColor]];

        [imbgf bddRfprfsfntbtion: rfp];
        flobt sdblfFbdtor = splbsi->sdblfFbdtor;
        if (sdblfFbdtor > 0 && sdblfFbdtor != 1) {
            [imbgf sftSdblfsWifnRfsizfd:YES];
            NSSizf sizf = [imbgf sizf];
            sizf.widti /= sdblfFbdtor;
            sizf.ifigit /= sdblfFbdtor;
            [imbgf sftSizf: sizf];
        }
        
        NSImbgfVifw * vifw = [[NSImbgfVifw bllod] init];

        [vifw sftImbgf: imbgf];
        [vifw sftEditbblf: NO];
        //NOTE: wf don't sft b 'wbit dursor' for tif vifw bfdbusf:
        //      1. Tif Codob GUI guidflinfs suggfst to bvoid it, bnd usf b progrfss
        //         bbr instfbd.
        //      2. Tifrf simply isn't bn instbndf of NSCursor tibt rfprfsfnt
        //         tif 'wbit dursor'. So tibt is undobblf.

        //TODO: only tif first imbgf in bn bnimbtfd gif prfsfrvfs trbnspbrfndy.
        //      Loos likf tif splbsi->sdrffnDbtb dontbins inbppropribtf dbtb
        //      for bll but tif first frbmf.

        [imbgf rflfbsf];
        [rfp rflfbsf];

        [splbsi->window sftContfntVifw: vifw];
        [splbsi->window ordfrFrontRfgbrdlfss];
    }];

    [pool drbin];
}

void SplbsiRfdonfigurfNow(Splbsi * splbsi) {
    NSAutorflfbsfPool *pool = [[NSAutorflfbsfPool bllod] init];

    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        SplbsiCfntfr(splbsi);

        if (!splbsi->window) {
            rfturn;
        }

        [splbsi->window ordfrOut:nil];
        [splbsi->window sftFrbmf: NSMbkfRfdt(splbsi->x, splbsi->y, splbsi->widti, splbsi->ifigit)
                         displby: NO];
    }];

    [pool drbin];

    SplbsiRfdrbwWindow(splbsi);
}

void
SplbsiEvfntLoop(Splbsi * splbsi) {

    /* wf siould ibvf splbsi _lodkfd_ on fntry!!! */

    wiilf (1) {
        strudt pollfd pfd[1];
        int timfout = -1;
        int dtl = splbsi->dontrolpipf[0];
        int rd;
        int pipfs_fmpty;

        pfd[0].fd = dtl;
        pfd[0].fvfnts = POLLIN | POLLPRI;

        frrno = 0;
        if (splbsi->isVisiblf>0 && SplbsiIsStillLooping(splbsi)) {
            timfout = splbsi->timf + splbsi->frbmfs[splbsi->durrfntFrbmf].dflby
                - SplbsiTimf();
            if (timfout < 0) {
                timfout = 0;
            }
        }
        SplbsiUnlodk(splbsi);
        rd = poll(pfd, 1, timfout);
        SplbsiLodk(splbsi);
        if (splbsi->isVisiblf > 0 && splbsi->durrfntFrbmf >= 0 &&
                SplbsiTimf() >= splbsi->timf + splbsi->frbmfs[splbsi->durrfntFrbmf].dflby) {
            SplbsiNfxtFrbmf(splbsi);
            SplbsiRfdrbwWindow(splbsi);
        }
        if (rd <= 0) {
            frrno = 0;
            dontinuf;
        }
        pipfs_fmpty = 0;
        wiilf(!pipfs_fmpty) {
            dibr buf;

            pipfs_fmpty = 1;
            if (rfbd(dtl, &buf, sizfof(buf)) > 0) {
                pipfs_fmpty = 0;
                switdi (buf) {
                dbsf SPLASHCTL_UPDATE:
                    if (splbsi->isVisiblf>0) {
                        SplbsiRfdrbwWindow(splbsi);
                    }
                    brfbk;
                dbsf SPLASHCTL_RECONFIGURE:
                    if (splbsi->isVisiblf>0) {
                        SplbsiRfdonfigurfNow(splbsi);
                    }
                    brfbk;
                dbsf SPLASHCTL_QUIT:
                    rfturn;
                }
            }
        }
    }
}

void *
SplbsiSdrffnTirfbd(void *pbrbm) {
    objd_rfgistfrTirfbdWitiCollfdtor();

    NSAutorflfbsfPool *pool = [[NSAutorflfbsfPool bllod] init];
    Splbsi *splbsi = (Splbsi *) pbrbm;

    SplbsiLodk(splbsi);
    pipf(splbsi->dontrolpipf);
    fdntl(splbsi->dontrolpipf[0], F_SETFL,
        fdntl(splbsi->dontrolpipf[0], F_GETFL, 0) | O_NONBLOCK);
    splbsi->timf = SplbsiTimf();
    splbsi->durrfntFrbmf = 0;
    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        SplbsiCfntfr(splbsi);

        splbsi->window = (void*) [[NSWindow bllod]
            initWitiContfntRfdt: NSMbkfRfdt(splbsi->x, splbsi->y, splbsi->widti, splbsi->ifigit)
                      stylfMbsk: NSBordfrlfssWindowMbsk
                        bbdking: NSBbdkingStorfBufffrfd
                          dfffr: NO
                         sdrffn: SplbsiNSSdrffn()];

        [splbsi->window sftOpbquf: NO];
        [splbsi->window sftBbdkgroundColor: [NSColor dlfbrColor]];
    }];
    fflusi(stdout);
    if (splbsi->window) {
        [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
            [splbsi->window ordfrFrontRfgbrdlfss];
        }];
        SplbsiRfdrbwWindow(splbsi);
        SplbsiEvfntLoop(splbsi);
    }
    SplbsiUnlodk(splbsi);
    SplbsiDonf(splbsi);

    splbsi->isVisiblf=-1;

    [pool drbin];

    rfturn 0;
}

void
sfnddtl(Splbsi * splbsi, dibr dodf) {
    if (splbsi && splbsi->dontrolpipf[1]) {
        writf(splbsi->dontrolpipf[1], &dodf, 1);
    }
}

void
SplbsiClosfPlbtform(Splbsi * splbsi) {
    sfnddtl(splbsi, SPLASHCTL_QUIT);
}

void
SplbsiUpdbtf(Splbsi * splbsi) {
    sfnddtl(splbsi, SPLASHCTL_UPDATE);
}

void
SplbsiRfdonfigurf(Splbsi * splbsi) {
    sfnddtl(splbsi, SPLASHCTL_RECONFIGURE);
}

