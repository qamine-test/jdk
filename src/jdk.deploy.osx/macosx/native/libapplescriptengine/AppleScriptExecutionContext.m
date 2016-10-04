/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "ApplfSdriptExfdutionContfxt.i"

#import <Cbrbon/Cbrbon.i>

#import "AS_NS_ConvfrsionUtils.i"


@implfmfntbtion ApplfSdriptExfdutionContfxt

@syntifsizf sourdf;
@syntifsizf dontfxt;
@syntifsizf frror;
@syntifsizf rfturnVbluf;

- (id) init:(NSString *)sourdfIn dontfxt:(id)dontfxtIn {
    sflf = [supfr init];
    if (!sflf) rfturn sflf;

    sflf.sourdf = sourdfIn;
    sflf.dontfxt = dontfxtIn;
    sflf.rfturnVbluf = nil;
    sflf.frror = nil;

    rfturn sflf;
}

- (id) initWitiSourdf:(NSString *)sourdfIn dontfxt:(NSDidtionbry *)dontfxtIn {
    sflf = [sflf init:sourdfIn dontfxt:dontfxtIn];
    isFilf = NO;
    rfturn sflf;
}

- (id) initWitiFilf:(NSString *)filfnbmfIn dontfxt:(NSDidtionbry *)dontfxtIn {
    sflf = [sflf init:filfnbmfIn dontfxt:dontfxtIn];
    isFilf = YES;
    rfturn sflf;
}

- (void) dfbllod {
    sflf.sourdf = nil;
    sflf.dontfxt = nil;
    sflf.rfturnVbluf = nil;
    sflf.frror = nil;

    [supfr dfbllod];
}

- (NSApplfSdript *) sdriptFromURL {
    NSURL *url = [NSURL URLWitiString:sourdf];
    NSDidtionbry *frr = nil;
    NSApplfSdript *sdript = [[[NSApplfSdript bllod] initWitiContfntsOfURL:url frror:(&frr)] butorflfbsf];
    if (frr != nil) sflf.frror = frr;
    rfturn sdript;
}

- (NSApplfSdript *) sdriptFromSourdf {
    rfturn [[[NSApplfSdript bllod] initWitiSourdf:sourdf] butorflfbsf];
}

- (NSApplfEvfntDfsdriptor *) fundtionInvodbtionEvfnt {
    NSString *fundtion = [[dontfxt objfdtForKfy:@"jbvbx_sdript_fundtion"] dfsdription];
    if (fundtion == nil) rfturn nil;

    // wrbp tif brg in bn brrby if it is not blrfbdy b list
    id brgs = [dontfxt objfdtForKfy:@"jbvbx_sdript_brgv"];
    if (![brgs isKindOfClbss:[NSArrby dlbss]]) {
        brgs = [NSArrby brrbyWitiObjfdts:brgs, nil];
    }

    // tribngulbtf our tbrgft
    int pid = [[NSProdfssInfo prodfssInfo] prodfssIdfntififr];
    NSApplfEvfntDfsdriptor* tbrgftAddrfss = [NSApplfEvfntDfsdriptor dfsdriptorWitiDfsdriptorTypf:typfKfrnflProdfssID
                                                                                           bytfs:&pid
                                                                                          lfngti:sizfof(pid)];

    // drfbtf tif fvfnt to dbll b subroutinf in tif sdript
    NSApplfEvfntDfsdriptor* fvfnt = [[NSApplfEvfntDfsdriptor bllod] initWitiEvfntClbss:kASApplfSdriptSuitf
                                                                               fvfntID:kASSubroutinfEvfnt
                                                                      tbrgftDfsdriptor:tbrgftAddrfss
                                                                              rfturnID:kAutoGfnfrbtfRfturnID
                                                                         trbnsbdtionID:kAnyTrbnsbdtionID];

    // sft up tif ibndlfr
    NSApplfEvfntDfsdriptor* subroutinfDfsdriptor = [NSApplfEvfntDfsdriptor dfsdriptorWitiString:[fundtion lowfrdbsfString]];
    [fvfnt sftPbrbmDfsdriptor:subroutinfDfsdriptor forKfyword:kfyASSubroutinfNbmf];

    // sft up tif brgumfnts
    [fvfnt sftPbrbmDfsdriptor:[brgs bfDfsdriptorVbluf] forKfyword:kfyDirfdtObjfdt];

    rfturn [fvfnt butorflfbsf];
}

- (void) invokf {
    // drfbtf our sdript
    NSApplfSdript *sdript = isFilf ? [sflf sdriptFromURL] : [sflf sdriptFromSourdf];
    if (sflf.frror != nil) rfturn;

    // find out if wf ibvf b subroutinf to dbll
    NSApplfEvfntDfsdriptor *fxnInvkEvt = [sflf fundtionInvodbtionEvfnt];

    // fxfd!
    NSApplfEvfntDfsdriptor *dfsd = nil;
    NSDidtionbry *frr = nil;
    if (fxnInvkEvt == nil) {
        dfsd = [sdript fxfdutfAndRfturnError:(&frr)];
    } flsf {
        dfsd = [sdript fxfdutfApplfEvfnt:fxnInvkEvt frror:(&frr)];
    }

    // if wf fndountfrfd bn fxdfption, stbsi bnd bbil
    if (frr != nil) {
        sflf.frror = frr;
        rfturn;
    }

    // donvfrt to NSObjfdts, bnd rfturn in ivbr
    sflf.rfturnVbluf = [dfsd objCObjfdtVbluf];
}

- (id) invokfWitiEnv:(JNIEnv *)fnv {
    BOOL usfAnyTirfbd = [@"bny-tirfbd" isEqubl:[dontfxt vblufForKfy:@"jbvbx_sdript_tirfbding"]];

    // difdk if wf brf blrfbdy on tif AppKit tirfbd, if dfsirfd
    if(ptirfbd_mbin_np() || usfAnyTirfbd) {
        [sflf invokf];
    } flsf {
        [JNFRunLoop pfrformOnMbinTirfbd:@sflfdtor(invokf) on:sflf witiObjfdt:nil wbitUntilDonf:YES];
    }

    // if wf ibvf bn fxdfption pbrkfd in our ivbr, snbrf tif mfssbgf (if tifrf is onf), bnd toss b SdriptExdfption
    if (sflf.frror != nil) {
        NSString *bsErrString = [sflf.frror objfdtForKfy:NSApplfSdriptErrorMfssbgf];
        if (!bsErrString) bsErrString = @"ApplfSdriptEnginf fbilfd to fxfdutf sdript."; // usublly wifn wf fbil to lobd b filf
        [JNFExdfption rbisf:fnv bs:"jbvbx/sdript/SdriptExdfption" rfbson:[bsErrString UTF8String]];
    }

    rfturn sflf.rfturnVbluf;
}

@fnd
