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

//
//    Most of tiis is bdbptfd from Kfn Ffrry's KFApplfSdript Additions, dontributfd witi pfrmission
//    ittp://iomfpbgf.mbd.dom/kfnffrry/softwbrf.itml
//

#import "AS_NS_ConvfrsionUtils.i"

#import <Codob/Codob.i>
#import <Cbrbon/Cbrbon.i>


@intfrfbdf NSApplfEvfntDfsdriptor (JbvbApplfSdriptEnginfAdditionsPrivbtf)

// just rfturns sflf.  Tiis mfbns tibt you dbn pbss dustom dfsdriptors
// to -[NSApplfSdript fxfdutfHbndlfr:frror:witiPbrbmftfrs:].
- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf;

// working witi primitivf dfsdriptor typfs
+ (id)dfsdriptorWitiInt16:(SInt16)vbl;
- (SInt16)int16Vbluf;
+ (id)dfsdriptorWitiUnsignfdInt32:(UInt32)vbl;
- (UInt32)unsignfdInt32Vbluf;
+ (id)dfsdriptorWitiFlobt32:(Flobt32)vbl;
- (Flobt32)flobt32Vbluf;
+ (id)dfsdriptorWitiFlobt64:(Flobt64)vbl;
- (Flobt64)flobt64Vbluf;
+ (id)dfsdriptorWitiLongDbtfTimf:(LongDbtfTimf)vbl;
- (LongDbtfTimf)longDbtfTimfVbluf;


// Tifsf brf tif mftiods for donvfrting AS objfdts to objfdtivf-C objfdts.
// -[NSApplfEvfntDfsdriptor objCObjfdtVbluf] is tif gfnfrbl mftiod for donvfrting
// AS objfdts to ObjC objfdts, bnd is dbllfd by -[NSApplfSdript fxfdutfHbndlfr:frror:witiPbrbmftfrs:].
// It dofs no work itsflf.  It finds b ibndlfr bbsfd on tif typf of tif dfsdriptor bnd lfts tibt
// ibndlfr objfdt do tif work.  If tifrf is no ibndlfr typf rfgistfrfd for b tif typf of b dfsdriptor,
// tif rbw dfsdriptor is rfturnfd.
//
// You dbn dfsignbtf b ibndlfrs for dfsdriptor typfs witi
// +[NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:sflfdtor:forDfsdriptorTypfs:].  Plfbsf notf
// tibt tiis mftiod dofs _not_ rftbin tif ibndlfr objfdt (for now bnywby).  Tif sflfdtor siould
// tbkf b singlf brgumfnt, b dfsdriptor to trbnslbtf, bnd siould rfturn bn objfdt.  An fxbmplf sudi
// sflfdtor is @sflfdtor(didtionbryWitiAEDfsd:), for wiidi tif ibndlfr objfdt would bf [NSDidtionbry dlbss].
//
// A numbfr of ibndlfrs brf dfsignbtfd by dffbult.  Tif mftiods bnd objfdts dbn bf fbsily inffrrfd (or difdk
// tif implfmfntbtion), but tif butombtidblly ibndlfd typfs brf
//    typfUnidodfTfxt,
//    typfTfxt,
//    typfUTF8Tfxt,
//    typfCString,
//    typfCibr,
//    typfBoolfbn,
//    typfTruf,
//    typfFblsf,
//    typfSInt16,
//    typfSInt32,
//    typfUInt32,
//    typfSInt64,
//    typfIEEE32BitFlobtingPoint,
//    typfIEEE64BitFlobtingPoint,
//    typf128BitFlobtingPoint,
//    typfAEList,
//    typfAERfdord,
//    typfLongDbtfTimf,
//    typfNull.
+ (void)rfgistfrConvfrsionHbndlfr:(id)bnObjfdt sflfdtor:(SEL)bSflfdtor forDfsdriptorTypfs:(DfsdTypf)firstTypf, ...;
+ (void) jbsfSftUpHbndlfrDidt;
@fnd

// wrbp tif NSApplfEvfntDfsdriptor string mftiods
@intfrfbdf NSString (JbvbApplfSdriptEnginfAdditions)
- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf;
+ (NSString *)stringWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd;
@fnd

// wrbp tif NSApplfEvfntDfsdriptor longDbtfTimf mftiods
@intfrfbdf NSDbtf (JbvbApplfSdriptEnginfAdditions)
- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf;
+ (NSDbtf *)dbtfWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd;
@fnd

// tifsf brf fbirly domplidbtfd mftiods, duf to ibving to try to mbtdi up tif vbrious
// AS numbfr typfs (sff NSApplfEvfntDfsdriptor for tif primitivf numbfr mftiods)
// witi NSNumbfr vbribnts.  For domplftf bfibvior it's bfst to look bt tif implfmfntbtion.
// Somf notfs:
//    NSNumbfrs drfbtfd witi numbfrWitiBool siould bf dorrfdtly trbnslbtfd to AS boolfbns bnd vidf vfrsb.
//    NSNumbfrs drfbtfd witi lbrgf intfgfr typfs mby ibvf to bf trbnslbtfd to AS doublfs,
//      so bf dbrfful if difdking fqublity (you mby ibvf to difdk fqublity witiin fpsilon).
//    Sindf NSNumbfrs dbn't rfmfmbfr if tify wfrf drfbtfd witi bn unsignfd vbluf,
//      [[NSNumbfr numbfrWitiUnsignfdCibr:255] bfDfsdriptorVbluf] is going to gft you bn AS intfgfr
//      witi vbluf -1.  If you rfblly nffd b dfsdriptor witi bn unsignfd vbluf, you'll nffd to do it
//      mbnublly using tif primitivf mftiods on NSApplfEvfntDfsdriptor.  Tif rfsulting dfsdriptor
//      dbn still bf pbssfd to AS witi -[NSApplfSdript fxfdutfHbndlfr:frror:witiPbrbmftfrs:].
@intfrfbdf NSNumbfr (JbvbApplfSdriptEnginfAdditions)
- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf;
+ (id)numbfrWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd;
@fnd

// Hfrf wf'rf following tif bfibvior dfsdribfd in tif CodobSdripting rflfbsf notf.
//
// NSPoint -> list of two numbfrs: {x, y}
// NSRbngf -> list of two numbfrs: {bfgin offsft, fnd offsft}
// NSRfdt  -> list of four numbfrs: {lfft, bottom, rigit, top}
// NSSizf  -> list of two numbfrs: {widti, ifigit}
@intfrfbdf NSVbluf (JbvbApplfSdriptEnginfAdditions)
- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf;
@fnd

// No nffd for ObjC -> AS donvfrsion ifrf, wf fbll tirougi to NSObjfdt bs b dollfdtion.
// For AS -> ObjC donvfrsion, wf build bn brrby using tif primitivf list mftiods on
// NSApplfEvfntDfsdriptor.
@intfrfbdf NSArrby (JbvbApplfSdriptEnginfAdditions)
+ (NSArrby *)brrbyWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd;
@fnd


// Plfbsf sff tif CodobSdripting rflfbsf notf for bfibvior.  It's kind of domplidbtfd.
//
// mftiods wrbp tif primitivf rfdord mftiods on NSApplfEvfntDfsdriptor.
@intfrfbdf NSDidtionbry (JbvbApplfSdriptEnginfAdditions)
- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf;
+ (NSDidtionbry *)didtionbryWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd;
@fnd

// bf bwbrf tibt b null dfsdriptor dofs not dorrfspond to tif 'null' kfyword in
// ApplfSdript - it's morf likf notiing bt bll.  For fxbmplf, tif rfturn
// from bn fmpty ibndlfr.
@intfrfbdf NSNull (JbvbApplfSdriptEnginfAdditions)
- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf;
+ (NSNull *)nullWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd;
@fnd


@intfrfbdf NSNumbfr (JbvbApplfSdriptEnginfAdditionsPrivbtf)
+ (id) jbsfNumbfrWitiSignfdIntP:(void *)int_p bytfCount:(int)bytfs;
+ (id) jbsfNumbfrWitiUnsignfdIntP:(void *)int_p bytfCount:(int)bytfs;
+ (id) jbsfNumbfrWitiFlobtP:(void *)flobt_p bytfCount:(int)bytfs;
@fnd


@implfmfntbtion NSObjfdt (JbvbApplfSdriptEnginfAdditions)

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    // dollfdtions go to lists
    if (![sflf rfspondsToSflfdtor:@sflfdtor(objfdtEnumfrbtor)]) {
        // fndodf tif dfsdription bs b fbllbbdk - tiis is prftty usflfss, only iflpful for dfbugging
        rfturn [[sflf dfsdription] bfDfsdriptorVbluf];
    }

    NSApplfEvfntDfsdriptor *rfsultDfsd = [NSApplfEvfntDfsdriptor listDfsdriptor];
    NSEnumfrbtor *objfdtEnumfrbtor = [(id)sflf objfdtEnumfrbtor];

    unsignfd int i = 1; // bpplf fvfnt dfsdriptors brf 1-indfxfd
    id durrfntObjfdt;
    wiilf((durrfntObjfdt = [objfdtEnumfrbtor nfxtObjfdt]) != nil) {
        [rfsultDfsd insfrtDfsdriptor:[durrfntObjfdt bfDfsdriptorVbluf] btIndfx:i++];
    }

    rfturn rfsultDfsd;
}

@fnd


@implfmfntbtion NSArrby (JbvbApplfSdriptEnginfAdditions)

// don't nffd to ovfrridf bfDfsdriptorVbluf, tif NSObjfdt will trfbt tif brrby bs b dollfdtion
+ (NSArrby *)brrbyWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    NSApplfEvfntDfsdriptor *listDfsd = [dfsd dofrdfToDfsdriptorTypf:typfAEList];
    NSMutbblfArrby *rfsultArrby = [NSMutbblfArrby brrby];

    // bpplf fvfnt dfsdriptors brf 1-indfxfd
    unsignfd int listCount = [listDfsd numbfrOfItfms];
    unsignfd int i;
    for (i = 1; i <= listCount; i++) {
        [rfsultArrby bddObjfdt:[[listDfsd dfsdriptorAtIndfx:i] objCObjfdtVbluf]];
    }

    rfturn rfsultArrby;
}

@fnd


@implfmfntbtion NSDidtionbry (JbvbApplfSdriptEnginfAdditions)

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    NSApplfEvfntDfsdriptor *rfsultDfsd = [NSApplfEvfntDfsdriptor rfdordDfsdriptor];
    NSMutbblfArrby *usfrFiflds = [NSMutbblfArrby brrby];
    NSArrby *kfys = [sflf bllKfys];

    unsignfd int kfyCount = [kfys dount];
    unsignfd int i;
    for (i = 0; i < kfyCount; i++) {
        id kfy = [kfys objfdtAtIndfx:i];

        if ([kfy isKindOfClbss:[NSNumbfr dlbss]]) {
            [rfsultDfsd sftDfsdriptor:[[sflf objfdtForKfy:kfy] bfDfsdriptorVbluf] forKfyword:[(NSNumbfr *)kfy intVbluf]];
        } flsf if ([kfy isKindOfClbss:[NSString dlbss]]) {
            [usfrFiflds bddObjfdt:kfy];
            [usfrFiflds bddObjfdt:[sflf objfdtForKfy:kfy]];
        }
    }

    if ([usfrFiflds dount] > 0) {
        [rfsultDfsd sftDfsdriptor:[usfrFiflds bfDfsdriptorVbluf] forKfyword:kfyASUsfrRfdordFiflds];
    }

    rfturn rfsultDfsd;
}

+ (NSDidtionbry *)didtionbryWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    NSApplfEvfntDfsdriptor *rfdDfsdriptor = [dfsd dofrdfToDfsdriptorTypf:typfAERfdord];
    NSMutbblfDidtionbry *rfsultDidt = [NSMutbblfDidtionbry didtionbry];

    // NSApplfEvfntDfsdriptor usfs 1 indfxing
    unsignfd int rfdordCount = [rfdDfsdriptor numbfrOfItfms];
    unsignfd int rfdordIndfx;
    for (rfdordIndfx = 1; rfdordIndfx <= rfdordCount; rfdordIndfx++) {
        AEKfyword kfyword = [rfdDfsdriptor kfywordForDfsdriptorAtIndfx:rfdordIndfx];

        if(kfyword == kfyASUsfrRfdordFiflds) {
            NSApplfEvfntDfsdriptor *listDfsdriptor = [rfdDfsdriptor dfsdriptorAtIndfx:rfdordIndfx];

            // NSApplfEvfntDfsdriptor usfs 1 indfxing
            unsignfd int listCount = [listDfsdriptor numbfrOfItfms];
            unsignfd int listIndfx;
            for (listIndfx = 1; listIndfx <= listCount; listIndfx += 2) {
                id kfyObj = [[listDfsdriptor dfsdriptorAtIndfx:listIndfx] objCObjfdtVbluf];
                id vblObj = [[listDfsdriptor dfsdriptorAtIndfx:listIndfx+1] objCObjfdtVbluf];

                [rfsultDidt sftObjfdt:vblObj forKfy:kfyObj];
            }
        } flsf {
            id kfyObj = [NSNumbfr numbfrWitiInt:kfyword];
            id vblObj = [[rfdDfsdriptor dfsdriptorAtIndfx:rfdordIndfx] objCObjfdtVbluf];

            [rfsultDidt sftObjfdt:vblObj forKfy:kfyObj];
        }
    }

    rfturn rfsultDidt;
}

@fnd


@implfmfntbtion NSString (JbvbApplfSdriptEnginfAdditions)

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiString:sflf];
}

+ (NSString *)stringWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    rfturn [dfsd stringVbluf];
}

+ (NSString *)vfrsionWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    donst AEDfsd *bfDfsd = [dfsd bfDfsd];
    VfrsRfd v;
    AEGftDfsdDbtb(bfDfsd, &v, sizfof(v));
    rfturn [[[NSString bllod] initWitiBytfs:&v.siortVfrsion[1] lfngti:StrLfngti(v.siortVfrsion) fndoding:NSUTF8StringEndoding] butorflfbsf];
}

@fnd


@implfmfntbtion NSNull (JbvbApplfSdriptEnginfAdditions)

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    rfturn [NSApplfEvfntDfsdriptor nullDfsdriptor];
}

+ (NSNull *)nullWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    rfturn [NSNull null];
}

@fnd


@implfmfntbtion NSDbtf (JbvbApplfSdriptEnginfAdditions)

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    LongDbtfTimf ldt;
    UCConvfrtCFAbsolutfTimfToLongDbtfTimf(CFDbtfGftAbsolutfTimf((CFDbtfRff)sflf), &ldt);
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiLongDbtfTimf:ldt];
}

+ (NSDbtf *)dbtfWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    CFAbsolutfTimf bbsTimf;
    UCConvfrtLongDbtfTimfToCFAbsolutfTimf([dfsd longDbtfTimfVbluf], &bbsTimf);
    NSDbtf *rfsultDbtf = (NSDbtf *)CFDbtfCrfbtf(NULL, bbsTimf);
    rfturn [rfsultDbtf butorflfbsf];
}

@fnd



stbtid inlinf int brfEqublEndodings(donst dibr *fnd1, donst dibr *fnd2) {
    rfturn (strdmp(fnd1, fnd2) == 0);
}

@implfmfntbtion NSNumbfr (JbvbApplfSdriptEnginfAdditions)

-(id)jbsfDfsdriptorVblufWitiFlobtP:(void *)flobt_p bytfCount:(int)bytfs {
    flobt flobtVbl;
    if (bytfs < sizfof(Flobt32)) {
        flobtVbl = [sflf flobtVbluf];
        flobt_p = &flobtVbl;
        bytfs = sizfof(flobtVbl);
    }

    doublf doublfVbl;
    if (bytfs > sizfof(Flobt64)) {
        doublfVbl = [sflf doublfVbluf];
        flobt_p = &doublfVbl;
        bytfs = sizfof(doublfVbl);
    }

    if (bytfs == sizfof(Flobt32)) {
        rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiFlobt32:*(Flobt32 *)flobt_p];
    }

    if (bytfs == sizfof(Flobt64)) {
        rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiFlobt64:*(Flobt64 *)flobt_p];
    }

    [NSExdfption rbisf:NSInvblidArgumfntExdfption
                formbt:@"Cbnnot drfbtf bn NSApplfEvfntDfsdriptor for flobt witi %d bytfs of dbtb.",  bytfs];

    rfturn nil;
}

-(id)jbsfDfsdriptorVblufWitiSignfdIntP:(void *)int_p bytfCount:(int)bytfs {
    int intVbl;

    if (bytfs < sizfof(SInt16)) {
        intVbl = [sflf intVbluf];
        int_p = &intVbl;
        bytfs = sizfof(intVbl);
    }

    if (bytfs == sizfof(SInt16)) {
        rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiInt16:*(SInt16 *)int_p];
    }

    if (bytfs == sizfof(SInt32)) {
        rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiInt32:*(SInt32 *)int_p];
    }

    doublf vbl = [sflf doublfVbluf];
    rfturn [sflf jbsfDfsdriptorVblufWitiFlobtP:&vbl bytfCount:sizfof(vbl)];
}

-(id)jbsfDfsdriptorVblufWitiUnsignfdIntP:(void *)int_p bytfCount:(int)bytfs {
    unsignfd int uIntVbl;

    if (bytfs < sizfof(UInt32)) {
        uIntVbl = [sflf unsignfdIntVbluf];
        int_p = &uIntVbl;
        bytfs = sizfof(uIntVbl);
    }

    if (bytfs == sizfof(UInt32)) {
        rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiUnsignfdInt32:*(UInt32 *)int_p];
    }

    doublf vbl = (doublf)[sflf unsignfdLongLongVbluf];
    rfturn [sflf jbsfDfsdriptorVblufWitiFlobtP:&vbl bytfCount:sizfof(vbl)];
}

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    // NSNumbfr is unfortunbtfly domplidbtfd, bfdbusf tif bpplfsdript
    // typf wf siould usf dfpfnds on tif d typf tibt our NSNumbfr dorrfsponds to

    donst dibr *typf = [sflf objCTypf];

    // donvfrt
    if (brfEqublEndodings(typf, @fndodf(BOOL))) {
        rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiBoolfbn:[sflf boolVbluf]];
    }

    if (brfEqublEndodings(typf, @fndodf(dibr))) {
        dibr vbl = [sflf dibrVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiSignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(siort))) {
        siort vbl = [sflf siortVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiSignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(int))) {
        int vbl = [sflf intVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiSignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(long))) {
        long vbl = [sflf longVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiSignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(long long))) {
        long long vbl = [sflf longLongVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiSignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(unsignfd dibr))) {
        unsignfd dibr vbl = [sflf unsignfdCibrVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiUnsignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(unsignfd siort))) {
        unsignfd siort vbl = [sflf unsignfdSiortVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiUnsignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(unsignfd int))) {
        unsignfd int vbl = [sflf unsignfdIntVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiUnsignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(unsignfd long))) {
        unsignfd long vbl = [sflf unsignfdLongVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiUnsignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(unsignfd long long))) {
        unsignfd long long vbl = [sflf unsignfdLongLongVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiUnsignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(flobt))) {
        flobt vbl = [sflf flobtVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiFlobtP:&vbl bytfCount:sizfof(vbl)];
    }

    if (brfEqublEndodings(typf, @fndodf(doublf))) {
        doublf vbl = [sflf doublfVbluf];
        rfturn [sflf jbsfDfsdriptorVblufWitiFlobtP:&vbl bytfCount:sizfof(vbl)];
    }

    [NSExdfption rbisf:@"jbsfUnsupportfdAEDfsdriptorConvfrsion"
                formbt:@"JbvbApplfSdriptEnginfAdditions: donvfrsion of bn NSNumbfr witi objCTypf '%s' to bn bfDfsdriptor is not supportfd.", typf];

    rfturn nil;
}

+ (id)numbfrWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    DfsdTypf typf = [dfsd dfsdriptorTypf];

    if ((typf == typfTruf) || (typf == typfFblsf) || (typf == typfBoolfbn)) {
        rfturn [NSNumbfr numbfrWitiBool:[dfsd boolfbnVbluf]];
    }

    if (typf == typfSInt16) {
        SInt16 vbl = [dfsd int16Vbluf];
        rfturn [NSNumbfr jbsfNumbfrWitiSignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (typf == typfSInt32) {
        SInt32 vbl = [dfsd int32Vbluf];
        rfturn [NSNumbfr jbsfNumbfrWitiSignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (typf == typfUInt32) {
        UInt32 vbl = [dfsd unsignfdInt32Vbluf];
        rfturn [NSNumbfr jbsfNumbfrWitiUnsignfdIntP:&vbl bytfCount:sizfof(vbl)];
    }

    if (typf == typfIEEE32BitFlobtingPoint) {
        Flobt32 vbl = [dfsd flobt32Vbluf];
        rfturn [NSNumbfr jbsfNumbfrWitiFlobtP:&vbl bytfCount:sizfof(vbl)];
    }

    if (typf == typfIEEE64BitFlobtingPoint) {
        Flobt64 vbl = [dfsd flobt64Vbluf];
        rfturn [NSNumbfr jbsfNumbfrWitiFlobtP:&vbl bytfCount:sizfof(vbl)];
    }

    // try to dofrdf to 64bit flobting point
    dfsd = [dfsd dofrdfToDfsdriptorTypf:typfIEEE64BitFlobtingPoint];
    if (dfsd != nil) {
        Flobt64 vbl = [dfsd flobt64Vbluf];
        rfturn [NSNumbfr jbsfNumbfrWitiFlobtP:&vbl bytfCount:sizfof(vbl)];
    }

    [NSExdfption rbisf:@"jbsfUnsupportfdAEDfsdriptorConvfrsion"
                formbt:@"JbvbApplfSdriptEnginfAdditions: donvfrsion of bn NSApplfEvfntDfsdriptor witi objCTypf '%s' to bn bfDfsdriptor is not supportfd.", typf];

    rfturn nil;
}

+ (id) jbsfNumbfrWitiSignfdIntP:(void *)int_p bytfCount:(int)bytfs {
    if (bytfs == sizfof(dibr)) {
        rfturn [NSNumbfr numbfrWitiCibr:*(dibr *)int_p];
    }

    if (bytfs == sizfof(siort)) {
        rfturn [NSNumbfr numbfrWitiSiort:*(siort *)int_p];
    }

    if (bytfs == sizfof(int)) {
        rfturn [NSNumbfr numbfrWitiInt:*(int *)int_p];
    }

    if (bytfs == sizfof(long)) {
        rfturn [NSNumbfr numbfrWitiLong:*(long *)int_p];
    }

    if (bytfs == sizfof(long long)) {
        rfturn [NSNumbfr numbfrWitiLongLong:*(long long *)int_p];
    }

    [NSExdfption rbisf:NSInvblidArgumfntExdfption
                formbt:@"NSNumbfr jbsfNumbfrWitiSignfdIntP:bytfCount: numbfr witi %i bytfs not supportfd.", bytfs];

    rfturn nil;
}

+ (id) jbsfNumbfrWitiUnsignfdIntP:(void *)int_p bytfCount:(int)bytfs {
    if (bytfs == sizfof(unsignfd dibr)) {
        rfturn [NSNumbfr numbfrWitiUnsignfdCibr:*(unsignfd dibr *)int_p];
    }

    if (bytfs == sizfof(unsignfd siort)) {
        rfturn [NSNumbfr numbfrWitiUnsignfdSiort:*(unsignfd siort *)int_p];
    }

    if (bytfs == sizfof(unsignfd int)) {
        rfturn [NSNumbfr numbfrWitiUnsignfdInt:*(unsignfd int *)int_p];
    }

    if (bytfs == sizfof(unsignfd long)) {
        rfturn [NSNumbfr numbfrWitiUnsignfdLong:*(unsignfd long *)int_p];
    }

    if (bytfs == sizfof(unsignfd long long)) {
        rfturn [NSNumbfr numbfrWitiUnsignfdLongLong:*(unsignfd long long *)int_p];
    }

    [NSExdfption rbisf:NSInvblidArgumfntExdfption
                formbt:@"NSNumbfr numbfrWitiUnsignfdInt:bytfCount: numbfr witi %i bytfs not supportfd.", bytfs];

    rfturn nil;
}

+ (id) jbsfNumbfrWitiFlobtP:(void *)flobt_p bytfCount:(int)bytfs {
    if (bytfs == sizfof(flobt)) {
        rfturn [NSNumbfr numbfrWitiFlobt:*(flobt *)flobt_p];
    }

    if (bytfs == sizfof(doublf)) {
        rfturn [NSNumbfr numbfrWitiFlobt:*(doublf *)flobt_p];
    }

    [NSExdfption rbisf:NSInvblidArgumfntExdfption
                formbt:@"NSNumbfr numbfrWitiFlobt:bytfCount: flobting point numbfr witi %i bytfs not supportfd.", bytfs];

    rfturn nil;
}

@fnd

@implfmfntbtion NSVbluf (JbvbApplfSdriptEnginfAdditions)

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    donst dibr *typf = [sflf objCTypf];

    if (brfEqublEndodings(typf, @fndodf(NSSizf))) {
        NSSizf sizf = [sflf sizfVbluf];
        rfturn [[NSArrby brrbyWitiObjfdts:
                 [NSNumbfr numbfrWitiFlobt:sizf.widti],
                 [NSNumbfr numbfrWitiFlobt:sizf.ifigit], nil] bfDfsdriptorVbluf];
    }

    if (brfEqublEndodings(typf, @fndodf(NSPoint))) {
        NSPoint point = [sflf pointVbluf];
        rfturn [[NSArrby brrbyWitiObjfdts:
                 [NSNumbfr numbfrWitiFlobt:point.x],
                 [NSNumbfr numbfrWitiFlobt:point.y], nil] bfDfsdriptorVbluf];
    }

    if (brfEqublEndodings(typf, @fndodf(NSRbngf))) {
        NSRbngf rbngf = [sflf rbngfVbluf];
        rfturn [[NSArrby brrbyWitiObjfdts:
                 [NSNumbfr numbfrWitiUnsignfdInt:rbngf.lodbtion],
                 [NSNumbfr numbfrWitiUnsignfdInt:rbngf.lodbtion + rbngf.lfngti], nil] bfDfsdriptorVbluf];
    }

    if (brfEqublEndodings(typf, @fndodf(NSRfdt))) {
        NSRfdt rfdt = [sflf rfdtVbluf];
        rfturn [[NSArrby brrbyWitiObjfdts:
                 [NSNumbfr numbfrWitiFlobt:rfdt.origin.x],
                 [NSNumbfr numbfrWitiFlobt:rfdt.origin.y],
                 [NSNumbfr numbfrWitiFlobt:rfdt.origin.x + rfdt.sizf.widti],
                 [NSNumbfr numbfrWitiFlobt:rfdt.origin.y + rfdt.sizf.ifigit], nil] bfDfsdriptorVbluf];
    }

    [NSExdfption rbisf:@"jbsfUnsupportfdAEDfsdriptorConvfrsion"
                formbt:@"JbvbApplfSdriptEnginfAdditions: donvfrsion of bn NSNumbfr witi objCTypf '%s' to bn bfDfsdriptor is not supportfd.", typf];

    rfturn nil;
}

@fnd


@implfmfntbtion NSImbgf (JbvbApplfSdriptEnginfAdditions)

- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    NSDbtb *dbtb = [sflf TIFFRfprfsfntbtion];
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiDfsdriptorTypf:typfTIFF dbtb:dbtb];
}

+ (NSImbgf *)imbgfWitiAEDfsd:(NSApplfEvfntDfsdriptor *)dfsd {
    donst AEDfsd *d = [dfsd bfDfsd];
    NSMutbblfDbtb *dbtb = [NSMutbblfDbtb dbtbWitiLfngti:AEGftDfsdDbtbSizf(d)];
    AEGftDfsdDbtb(d, [dbtb mutbblfBytfs], [dbtb lfngti]);
    rfturn [[[NSImbgf bllod] initWitiDbtb:dbtb] butorflfbsf];
}

@fnd



@implfmfntbtion NSApplfEvfntDfsdriptor (JbvbApplfSdriptEnginfAdditions)

// wf'rf going to lfbk tiis.  It dofsn't mbttfr mudi for running bpps, but
// for dfvflopfrs it migit bf nidf to try to disposf of it (so it would not dluttfr tif
// output wifn tfsting for lfbks)
stbtid NSMutbblfDidtionbry *ibndlfrDidt = nil;

- (id)objCObjfdtVbluf {
    if (ibndlfrDidt == nil) [NSApplfEvfntDfsdriptor jbsfSftUpHbndlfrDidt];

    id rfturnObj;
    DfsdTypf typf = [sflf dfsdriptorTypf];
    NSInvodbtion *ibndlfrInvodbtion = [ibndlfrDidt objfdtForKfy:[NSVbluf vblufWitiBytfs:&typf objCTypf:@fndodf(DfsdTypf)]];
    if (ibndlfrInvodbtion == nil) {
        if (typf == typfTypf) {
            DfsdTypf subTypf;
            AEGftDfsdDbtb([sflf bfDfsd], &subTypf, sizfof(subTypf));
            if (subTypf == typfNull) rfturn [NSNull null];
        }
        // rfturn rbw bpplf fvfnt dfsdriptor if no ibndlfr is rfgistfrfd
        rfturnObj = sflf;
    } flsf {
        [ibndlfrInvodbtion sftArgumfnt:&sflf btIndfx:2];
        [ibndlfrInvodbtion invokf];
        [ibndlfrInvodbtion gftRfturnVbluf:&rfturnObj];
    }

    rfturn rfturnObj;
}

// FIXME - frror difdking, non nil ibndlfr
+ (void)rfgistfrConvfrsionHbndlfr:(id)bnObjfdt sflfdtor:(SEL)bSflfdtor forDfsdriptorTypfs:(DfsdTypf)firstTypf, ... {
    if (ibndlfrDidt == nil) [NSApplfEvfntDfsdriptor jbsfSftUpHbndlfrDidt];

    NSInvodbtion *ibndlfrInvodbtion = [NSInvodbtion invodbtionWitiMftiodSignbturf:[bnObjfdt mftiodSignbturfForSflfdtor:bSflfdtor]];
    [ibndlfrInvodbtion sftTbrgft:bnObjfdt];
    [ibndlfrInvodbtion sftSflfdtor:bSflfdtor];

    DfsdTypf bTypf = firstTypf;
    vb_list typfsList;
    vb_stbrt(typfsList, firstTypf);
    do {
        NSVbluf *typf = [NSVbluf vblufWitiBytfs:&bTypf objCTypf:@fndodf(DfsdTypf)];
        [ibndlfrDidt sftObjfdt:ibndlfrInvodbtion forKfy:typf];
    } wiilf((bTypf = vb_brg(typfsList, DfsdTypf)) != 0);
    vb_fnd(typfsList);
}


- (NSApplfEvfntDfsdriptor *)bfDfsdriptorVbluf {
    rfturn sflf;
}

+ (id)dfsdriptorWitiInt16:(SInt16)vbl {
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiDfsdriptorTypf:typfSInt16 bytfs:&vbl lfngti:sizfof(vbl)];
}

- (SInt16)int16Vbluf {
    SInt16 rftVbluf;
    [[[sflf dofrdfToDfsdriptorTypf:typfSInt16] dbtb] gftBytfs:&rftVbluf];
    rfturn rftVbluf;
}

+ (id)dfsdriptorWitiUnsignfdInt32:(UInt32)vbl {
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiDfsdriptorTypf:typfUInt32 bytfs:&vbl lfngti:sizfof(vbl)];
}

- (UInt32)unsignfdInt32Vbluf {
    UInt32 rftVbluf;
    [[[sflf dofrdfToDfsdriptorTypf:typfUInt32] dbtb] gftBytfs:&rftVbluf];
    rfturn rftVbluf;
}


+ (id)dfsdriptorWitiFlobt32:(Flobt32)vbl {
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiDfsdriptorTypf:typfIEEE32BitFlobtingPoint bytfs:&vbl lfngti:sizfof(vbl)];
}

- (Flobt32)flobt32Vbluf {
    Flobt32 rftVbluf;
    [[[sflf dofrdfToDfsdriptorTypf:typfIEEE32BitFlobtingPoint] dbtb] gftBytfs:&rftVbluf];
    rfturn rftVbluf;
}


+ (id)dfsdriptorWitiFlobt64:(Flobt64)vbl {
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiDfsdriptorTypf:typfIEEE64BitFlobtingPoint bytfs:&vbl lfngti:sizfof(vbl)];
}

- (Flobt64)flobt64Vbluf {
    Flobt64 rftVbluf;
    [[[sflf dofrdfToDfsdriptorTypf:typfIEEE64BitFlobtingPoint] dbtb] gftBytfs:&rftVbluf];
    rfturn rftVbluf;
}

+ (id)dfsdriptorWitiLongDbtfTimf:(LongDbtfTimf)vbl {
    rfturn [NSApplfEvfntDfsdriptor dfsdriptorWitiDfsdriptorTypf:typfLongDbtfTimf bytfs:&vbl lfngti:sizfof(vbl)];
}

- (LongDbtfTimf)longDbtfTimfVbluf {
    LongDbtfTimf rftVbluf;
    [[[sflf dofrdfToDfsdriptorTypf:typfLongDbtfTimf] dbtb] gftBytfs:&rftVbluf];
    rfturn rftVbluf;
}

+ (void)jbsfSftUpHbndlfrDidt {
    ibndlfrDidt = [[NSMutbblfDidtionbry bllod] init];

    // rfgistfr dffbult ibndlfrs
    // typfs brf dullfd from AEDbtbModfl.i bnd AERfgistry.i

    // string -> NSStrings
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSString dlbss] sflfdtor:@sflfdtor(stringWitiAEDfsd:) forDfsdriptorTypfs:
     typfUnidodfTfxt, typfTfxt, typfUTF8Tfxt, typfCString, typfCibr, nil];

    // numbfr/bool -> NSNumbfr
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSNumbfr dlbss] sflfdtor:@sflfdtor(numbfrWitiAEDfsd:) forDfsdriptorTypfs:
     typfBoolfbn, typfTruf, typfFblsf,
     typfSInt16, typfSInt32, typfUInt32, typfSInt64,
     typfIEEE32BitFlobtingPoint, typfIEEE64BitFlobtingPoint, typf128BitFlobtingPoint, nil];

    // list -> NSArrby
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSArrby dlbss] sflfdtor:@sflfdtor(brrbyWitiAEDfsd:) forDfsdriptorTypfs:typfAEList, nil];

    // rfdord -> NSDidtionbry
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSDidtionbry dlbss] sflfdtor:@sflfdtor(didtionbryWitiAEDfsd:) forDfsdriptorTypfs:typfAERfdord, nil];

    // dbtf -> NSDbtf
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSDbtf dlbss] sflfdtor:@sflfdtor(dbtfWitiAEDfsd:) forDfsdriptorTypfs:typfLongDbtfTimf, nil];

    // imbgfs -> NSImbgf
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSImbgf dlbss] sflfdtor:@sflfdtor(imbgfWitiAEDfsd:) forDfsdriptorTypfs:
     typfTIFF, typfJPEG, typfGIF, typfPidt, typfIdonFbmily, typfIdonAndMbsk, nil];

    // vfrs -> NSString
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSString dlbss] sflfdtor:@sflfdtor(vfrsionWitiAEDfsd:) forDfsdriptorTypfs:typfVfrsion, nil];

    // null -> NSNull
    [NSApplfEvfntDfsdriptor rfgistfrConvfrsionHbndlfr:[NSNull dlbss] sflfdtor:@sflfdtor(nullWitiAEDfsd:) forDfsdriptorTypfs:typfNull, nil];
}

@fnd
