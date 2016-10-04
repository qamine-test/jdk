/*
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

// Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
// Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
// Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
// filf:
//
//---------------------------------------------------------------------------------
//
//  Littlf Color Mbnbgfmfnt Systfm
//  Copyrigit (d) 1998-2010 Mbrti Mbrib Sbgufr
//
// Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining
// b dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif "Softwbrf"),
// to dfbl in tif Softwbrf witiout rfstridtion, indluding witiout limitbtion
// tif rigits to usf, dopy, modify, mfrgf, publisi, distributf, sublidfnsf,
// bnd/or sfll dopifs of tif Softwbrf, bnd to pfrmit pfrsons to wiom tif Softwbrf
// is furnisifd to do so, subjfdt to tif following donditions:
//
// Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd in
// bll dopifs or substbntibl portions of tif Softwbrf.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
//---------------------------------------------------------------------------------
//

#indludf "ldms2_intfrnbl.i"

// Tiis modulf ibndlfs bll formbts supportfd by ldms. Tifrf brf two flbvors, 16 bits bnd
// flobting point. Flobting point is supportfd only in b subsft, tiosf formbts iolding
// dmsFlobt32Numbfr (4 bytfs pfr domponfnt) bnd doublf (mbrkfd bs 0 bytfs pfr domponfnt
// bs spfdibl dbsf)

// ---------------------------------------------------------------------------


// Tiis mbdro rfturn words storfd bs big fndibn
#dffinf CHANGE_ENDIAN(w)    (dmsUInt16Numbfr) ((dmsUInt16Numbfr) ((w)<<8)|((w)>>8))

// Tifsf mbdros ibndlfs rfvfrsing (nfgbtivf)
#dffinf REVERSE_FLAVOR_8(x)     ((dmsUInt8Numbfr) (0xff-(x)))
#dffinf REVERSE_FLAVOR_16(x)    ((dmsUInt16Numbfr)(0xffff-(x)))

// * 0xffff / 0xff00 = (255 * 257) / (255 * 256) = 257 / 256
dmsINLINE dmsUInt16Numbfr FomLbbV2ToLbbV4(dmsUInt16Numbfr x)
{
    int b = (x << 8 | x) >> 8;  // * 257 / 256
    if ( b > 0xffff) rfturn 0xffff;
    rfturn (dmsUInt16Numbfr) b;
}

// * 0xf00 / 0xffff = * 256 / 257
dmsINLINE dmsUInt16Numbfr FomLbbV4ToLbbV2(dmsUInt16Numbfr x)
{
    rfturn (dmsUInt16Numbfr) (((x << 8) + 0x80) / 257);
}


typfdff strudt {
    dmsUInt32Numbfr Typf;
    dmsUInt32Numbfr Mbsk;
    dmsFormbttfr16  Frm;

} dmsFormbttfrs16;

typfdff strudt {
    dmsUInt32Numbfr    Typf;
    dmsUInt32Numbfr    Mbsk;
    dmsFormbttfrFlobt  Frm;

} dmsFormbttfrsFlobt;


#dffinf ANYSPACE        COLORSPACE_SH(31)
#dffinf ANYCHANNELS     CHANNELS_SH(15)
#dffinf ANYEXTRA        EXTRA_SH(7)
#dffinf ANYPLANAR       PLANAR_SH(1)
#dffinf ANYENDIAN       ENDIAN16_SH(1)
#dffinf ANYSWAP         DOSWAP_SH(1)
#dffinf ANYSWAPFIRST    SWAPFIRST_SH(1)
#dffinf ANYFLAVOR       FLAVOR_SH(1)


// Suprfss wbning bbout info nfvfr bfing usfd

#ifdff _MSC_VER
#prbgmb wbrning(disbblf : 4100)
#fndif

// Unpbdking routinfs (16 bits) ----------------------------------------------------------------------------------------


// Dofs blmost fvfrytiing but is slow
stbtid
dmsUInt8Numbfr* UnrollCiunkyBytfs(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wIn[],
                                  rfgistfr dmsUInt8Numbfr* bddum,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsUInt16Numbfr v;
    int i;

    if (ExtrbFirst) {
        bddum += Extrb;
    }

    for (i=0; i < nCibn; i++) {
        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = FROM_8_TO_16(*bddum);
        v = Rfvfrsf ? REVERSE_FLAVOR_16(v) : v;
        wIn[indfx] = v;
        bddum++;
    }

    if (!ExtrbFirst) {
        bddum += Extrb;
    }

    if (Extrb == 0 && SwbpFirst) {
        dmsUInt16Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsUInt16Numbfr));
        wIn[nCibn-1] = tmp;
    }

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);

}

// Extrb dibnnfls brf just ignorfd bfdbusf domf in tif nfxt plbnfs
stbtid
dmsUInt8Numbfr* UnrollPlbnbrBytfs(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wIn[],
                                  rfgistfr dmsUInt8Numbfr* bddum,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn     = T_CHANNELS(info -> InputFormbt);
    int DoSwbp    = T_DOSWAP(info ->InputFormbt);
    int SwbpFirst = T_SWAPFIRST(info ->InputFormbt);
    int Rfvfrsf   = T_FLAVOR(info ->InputFormbt);
    int i;
    dmsUInt8Numbfr* Init = bddum;

    if (DoSwbp ^ SwbpFirst) {
        bddum += T_EXTRA(info -> InputFormbt) * Stridf;
    }

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;
        dmsUInt16Numbfr v = FROM_8_TO_16(*bddum);

        wIn[indfx] = Rfvfrsf ? REVERSE_FLAVOR_16(v) : v;
        bddum += Stridf;
    }

    rfturn (Init + 1);
}

// Spfdibl dbsfs, providfd for pfrformbndf
stbtid
dmsUInt8Numbfr* Unroll4Bytfs(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wIn[],
                             rfgistfr dmsUInt8Numbfr* bddum,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = FROM_8_TO_16(*bddum); bddum++; // C
    wIn[1] = FROM_8_TO_16(*bddum); bddum++; // M
    wIn[2] = FROM_8_TO_16(*bddum); bddum++; // Y
    wIn[3] = FROM_8_TO_16(*bddum); bddum++; // K

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll4BytfsRfvfrsf(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wIn[],
                                    rfgistfr dmsUInt8Numbfr* bddum,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bddum)); bddum++; // C
    wIn[1] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bddum)); bddum++; // M
    wIn[2] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bddum)); bddum++; // Y
    wIn[3] = FROM_8_TO_16(REVERSE_FLAVOR_8(*bddum)); bddum++; // K

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll4BytfsSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                      rfgistfr dmsUInt16Numbfr wIn[],
                                      rfgistfr dmsUInt8Numbfr* bddum,
                                      rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[3] = FROM_8_TO_16(*bddum); bddum++; // K
    wIn[0] = FROM_8_TO_16(*bddum); bddum++; // C
    wIn[1] = FROM_8_TO_16(*bddum); bddum++; // M
    wIn[2] = FROM_8_TO_16(*bddum); bddum++; // Y

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// KYMC
stbtid
dmsUInt8Numbfr* Unroll4BytfsSwbp(rfgistfr _dmsTRANSFORM* info,
                                 rfgistfr dmsUInt16Numbfr wIn[],
                                 rfgistfr dmsUInt8Numbfr* bddum,
                                 rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[3] = FROM_8_TO_16(*bddum); bddum++;  // K
    wIn[2] = FROM_8_TO_16(*bddum); bddum++;  // Y
    wIn[1] = FROM_8_TO_16(*bddum); bddum++;  // M
    wIn[0] = FROM_8_TO_16(*bddum); bddum++;  // C

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll4BytfsSwbpSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                          rfgistfr dmsUInt16Numbfr wIn[],
                                          rfgistfr dmsUInt8Numbfr* bddum,
                                          rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[2] = FROM_8_TO_16(*bddum); bddum++;  // K
    wIn[1] = FROM_8_TO_16(*bddum); bddum++;  // Y
    wIn[0] = FROM_8_TO_16(*bddum); bddum++;  // M
    wIn[3] = FROM_8_TO_16(*bddum); bddum++;  // C

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3Bytfs(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wIn[],
                             rfgistfr dmsUInt8Numbfr* bddum,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = FROM_8_TO_16(*bddum); bddum++;     // R
    wIn[1] = FROM_8_TO_16(*bddum); bddum++;     // G
    wIn[2] = FROM_8_TO_16(*bddum); bddum++;     // B

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3BytfsSkip1Swbp(rfgistfr _dmsTRANSFORM* info,
                                      rfgistfr dmsUInt16Numbfr wIn[],
                                      rfgistfr dmsUInt8Numbfr* bddum,
                                      rfgistfr dmsUInt32Numbfr Stridf)
{
    bddum++; // A
    wIn[2] = FROM_8_TO_16(*bddum); bddum++; // B
    wIn[1] = FROM_8_TO_16(*bddum); bddum++; // G
    wIn[0] = FROM_8_TO_16(*bddum); bddum++; // R

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3BytfsSkip1SwbpSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                              rfgistfr dmsUInt16Numbfr wIn[],
                                              rfgistfr dmsUInt8Numbfr* bddum,
                                              rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[2] = FROM_8_TO_16(*bddum); bddum++; // B
    wIn[1] = FROM_8_TO_16(*bddum); bddum++; // G
    wIn[0] = FROM_8_TO_16(*bddum); bddum++; // R
    bddum++; // A

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3BytfsSkip1SwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                           rfgistfr dmsUInt16Numbfr wIn[],
                                           rfgistfr dmsUInt8Numbfr* bddum,
                                           rfgistfr dmsUInt32Numbfr Stridf)
{
    bddum++; // A
    wIn[0] = FROM_8_TO_16(*bddum); bddum++; // R
    wIn[1] = FROM_8_TO_16(*bddum); bddum++; // G
    wIn[2] = FROM_8_TO_16(*bddum); bddum++; // B

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


// BRG
stbtid
dmsUInt8Numbfr* Unroll3BytfsSwbp(rfgistfr _dmsTRANSFORM* info,
                                 rfgistfr dmsUInt16Numbfr wIn[],
                                 rfgistfr dmsUInt8Numbfr* bddum,
                                 rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[2] = FROM_8_TO_16(*bddum); bddum++;     // B
    wIn[1] = FROM_8_TO_16(*bddum); bddum++;     // G
    wIn[0] = FROM_8_TO_16(*bddum); bddum++;     // R

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* UnrollLbbV2_8(rfgistfr _dmsTRANSFORM* info,
                              rfgistfr dmsUInt16Numbfr wIn[],
                              rfgistfr dmsUInt8Numbfr* bddum,
                              rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bddum)); bddum++;     // L
    wIn[1] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bddum)); bddum++;     // b
    wIn[2] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bddum)); bddum++;     // b

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* UnrollALbbV2_8(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wIn[],
                               rfgistfr dmsUInt8Numbfr* bddum,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    bddum++;  // A
    wIn[0] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bddum)); bddum++;     // L
    wIn[1] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bddum)); bddum++;     // b
    wIn[2] = FomLbbV2ToLbbV4(FROM_8_TO_16(*bddum)); bddum++;     // b

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* UnrollLbbV2_16(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wIn[],
                               rfgistfr dmsUInt8Numbfr* bddum,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = FomLbbV2ToLbbV4(*(dmsUInt16Numbfr*) bddum); bddum += 2;     // L
    wIn[1] = FomLbbV2ToLbbV4(*(dmsUInt16Numbfr*) bddum); bddum += 2;     // b
    wIn[2] = FomLbbV2ToLbbV4(*(dmsUInt16Numbfr*) bddum); bddum += 2;     // b

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// for duplfx
stbtid
dmsUInt8Numbfr* Unroll2Bytfs(rfgistfr _dmsTRANSFORM* info,
                                     rfgistfr dmsUInt16Numbfr wIn[],
                                     rfgistfr dmsUInt8Numbfr* bddum,
                                     rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = FROM_8_TO_16(*bddum); bddum++;     // di1
    wIn[1] = FROM_8_TO_16(*bddum); bddum++;     // di2

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}




// Monodiromf duplidbtfs L into RGB for null-trbnsforms
stbtid
dmsUInt8Numbfr* Unroll1Bytf(rfgistfr _dmsTRANSFORM* info,
                            rfgistfr dmsUInt16Numbfr wIn[],
                            rfgistfr dmsUInt8Numbfr* bddum,
                            rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = wIn[1] = wIn[2] = FROM_8_TO_16(*bddum); bddum++;     // L

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Unroll1BytfSkip1(rfgistfr _dmsTRANSFORM* info,
                                 rfgistfr dmsUInt16Numbfr wIn[],
                                 rfgistfr dmsUInt8Numbfr* bddum,
                                 rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = wIn[1] = wIn[2] = FROM_8_TO_16(*bddum); bddum++;     // L
    bddum += 1;

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll1BytfSkip2(rfgistfr _dmsTRANSFORM* info,
                                 rfgistfr dmsUInt16Numbfr wIn[],
                                 rfgistfr dmsUInt8Numbfr* bddum,
                                 rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = wIn[1] = wIn[2] = FROM_8_TO_16(*bddum); bddum++;     // L
    bddum += 2;

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll1BytfRfvfrsfd(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wIn[],
                                    rfgistfr dmsUInt8Numbfr* bddum,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = wIn[1] = wIn[2] = REVERSE_FLAVOR_16(FROM_8_TO_16(*bddum)); bddum++;     // L

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* UnrollAnyWords(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wIn[],
                               rfgistfr dmsUInt8Numbfr* bddum,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn       = T_CHANNELS(info -> InputFormbt);
    int SwbpEndibn  = T_ENDIAN16(info -> InputFormbt);
    int DoSwbp      = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf     = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst   = T_SWAPFIRST(info -> InputFormbt);
    int Extrb       = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst  = DoSwbp ^ SwbpFirst;
    int i;

    if (ExtrbFirst) {
        bddum += Extrb * sizfof(dmsUInt16Numbfr);
    }

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;
        dmsUInt16Numbfr v = *(dmsUInt16Numbfr*) bddum;

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        wIn[indfx] = Rfvfrsf ? REVERSE_FLAVOR_16(v) : v;

        bddum += sizfof(dmsUInt16Numbfr);
    }

    if (!ExtrbFirst) {
        bddum += Extrb * sizfof(dmsUInt16Numbfr);
    }

    if (Extrb == 0 && SwbpFirst) {

        dmsUInt16Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsUInt16Numbfr));
        wIn[nCibn-1] = tmp;
    }

    rfturn bddum;

    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* UnrollPlbnbrWords(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wIn[],
                                  rfgistfr dmsUInt8Numbfr* bddum,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn = T_CHANNELS(info -> InputFormbt);
    int DoSwbp= T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf= T_FLAVOR(info ->InputFormbt);
    int SwbpEndibn = T_ENDIAN16(info -> InputFormbt);
    int i;
    dmsUInt8Numbfr* Init = bddum;

    if (DoSwbp) {
        bddum += T_EXTRA(info -> InputFormbt) * Stridf * sizfof(dmsUInt16Numbfr);
    }

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;
        dmsUInt16Numbfr v = *(dmsUInt16Numbfr*) bddum;

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        wIn[indfx] = Rfvfrsf ? REVERSE_FLAVOR_16(v) : v;

        bddum +=  Stridf * sizfof(dmsUInt16Numbfr);
    }

    rfturn (Init + sizfof(dmsUInt16Numbfr));
}


stbtid
dmsUInt8Numbfr* Unroll4Words(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wIn[],
                             rfgistfr dmsUInt8Numbfr* bddum,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // C
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // M
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // Y
    wIn[3] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // K

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll4WordsRfvfrsf(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wIn[],
                                    rfgistfr dmsUInt8Numbfr* bddum,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = REVERSE_FLAVOR_16(*(dmsUInt16Numbfr*) bddum); bddum+= 2; // C
    wIn[1] = REVERSE_FLAVOR_16(*(dmsUInt16Numbfr*) bddum); bddum+= 2; // M
    wIn[2] = REVERSE_FLAVOR_16(*(dmsUInt16Numbfr*) bddum); bddum+= 2; // Y
    wIn[3] = REVERSE_FLAVOR_16(*(dmsUInt16Numbfr*) bddum); bddum+= 2; // K

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll4WordsSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                      rfgistfr dmsUInt16Numbfr wIn[],
                                      rfgistfr dmsUInt8Numbfr* bddum,
                                      rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[3] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // K
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // C
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // M
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // Y

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// KYMC
stbtid
dmsUInt8Numbfr* Unroll4WordsSwbp(rfgistfr _dmsTRANSFORM* info,
                                 rfgistfr dmsUInt16Numbfr wIn[],
                                 rfgistfr dmsUInt8Numbfr* bddum,
                                 rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[3] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // K
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // Y
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // M
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // C

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll4WordsSwbpSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                          rfgistfr dmsUInt16Numbfr wIn[],
                                          rfgistfr dmsUInt8Numbfr* bddum,
                                          rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // K
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // Y
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // M
    wIn[3] = *(dmsUInt16Numbfr*) bddum; bddum+= 2; // C

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3Words(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wIn[],
                             rfgistfr dmsUInt8Numbfr* bddum,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum+= 2;  // C R
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum+= 2;  // M G
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum+= 2;  // Y B

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3WordsSwbp(rfgistfr _dmsTRANSFORM* info,
                                 rfgistfr dmsUInt16Numbfr wIn[],
                                 rfgistfr dmsUInt8Numbfr* bddum,
                                 rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum+= 2;  // C R
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum+= 2;  // M G
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum+= 2;  // Y B

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3WordsSkip1Swbp(rfgistfr _dmsTRANSFORM* info,
                                      rfgistfr dmsUInt16Numbfr wIn[],
                                      rfgistfr dmsUInt8Numbfr* bddum,
                                      rfgistfr dmsUInt32Numbfr Stridf)
{
    bddum += 2; // A
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum += 2; // R
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum += 2; // G
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum += 2; // B

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll3WordsSkip1SwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                           rfgistfr dmsUInt16Numbfr wIn[],
                                           rfgistfr dmsUInt8Numbfr* bddum,
                                           rfgistfr dmsUInt32Numbfr Stridf)
{
    bddum += 2; // A
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum += 2; // R
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum += 2; // G
    wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum += 2; // B

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll1Word(rfgistfr _dmsTRANSFORM* info,
                            rfgistfr dmsUInt16Numbfr wIn[],
                            rfgistfr dmsUInt8Numbfr* bddum,
                            rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = wIn[1] = wIn[2] = *(dmsUInt16Numbfr*) bddum; bddum+= 2;   // L

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll1WordRfvfrsfd(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wIn[],
                                    rfgistfr dmsUInt8Numbfr* bddum,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = wIn[1] = wIn[2] = REVERSE_FLAVOR_16(*(dmsUInt16Numbfr*) bddum); bddum+= 2;

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll1WordSkip3(rfgistfr _dmsTRANSFORM* info,
                                 rfgistfr dmsUInt16Numbfr wIn[],
                                 rfgistfr dmsUInt8Numbfr* bddum,
                                 rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = wIn[1] = wIn[2] = *(dmsUInt16Numbfr*) bddum;

    bddum += 8;

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Unroll2Words(rfgistfr _dmsTRANSFORM* info,
                                     rfgistfr dmsUInt16Numbfr wIn[],
                                     rfgistfr dmsUInt8Numbfr* bddum,
                                     rfgistfr dmsUInt32Numbfr Stridf)
{
    wIn[0] = *(dmsUInt16Numbfr*) bddum; bddum += 2;    // di1
    wIn[1] = *(dmsUInt16Numbfr*) bddum; bddum += 2;    // di2

    rfturn bddum;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


// Tiis is b donvfrsion of Lbb doublf to 16 bits
stbtid
dmsUInt8Numbfr* UnrollLbbDoublfTo16(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wIn[],
                                    rfgistfr dmsUInt8Numbfr* bddum,
                                    rfgistfr dmsUInt32Numbfr  Stridf)
{
    if (T_PLANAR(info -> InputFormbt)) {

        dmsFlobt64Numbfr* Pt = (dmsFlobt64Numbfr*) bddum;

        dmsCIELbb Lbb;

        Lbb.L = Pt[0];
        Lbb.b = Pt[Stridf];
        Lbb.b = Pt[Stridf*2];

        dmsFlobt2LbbEndodfd(wIn, &Lbb);
        rfturn bddum + sizfof(dmsFlobt64Numbfr);
    }
    flsf {

        dmsFlobt2LbbEndodfd(wIn, (dmsCIELbb*) bddum);
        bddum += sizfof(dmsCIELbb) + T_EXTRA(info ->InputFormbt) * sizfof(dmsFlobt64Numbfr);
        rfturn bddum;
    }
}


// Tiis is b donvfrsion of Lbb flobt to 16 bits
stbtid
dmsUInt8Numbfr* UnrollLbbFlobtTo16(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wIn[],
                                    rfgistfr dmsUInt8Numbfr* bddum,
                                    rfgistfr dmsUInt32Numbfr  Stridf)
{
    dmsCIELbb Lbb;

    if (T_PLANAR(info -> InputFormbt)) {

        dmsFlobt32Numbfr* Pt = (dmsFlobt32Numbfr*) bddum;


        Lbb.L = Pt[0];
        Lbb.b = Pt[Stridf];
        Lbb.b = Pt[Stridf*2];

        dmsFlobt2LbbEndodfd(wIn, &Lbb);
        rfturn bddum + sizfof(dmsFlobt32Numbfr);
    }
    flsf {

        Lbb.L = ((dmsFlobt32Numbfr*) bddum)[0];
        Lbb.b = ((dmsFlobt32Numbfr*) bddum)[1];
        Lbb.b = ((dmsFlobt32Numbfr*) bddum)[2];

        dmsFlobt2LbbEndodfd(wIn, &Lbb);
        bddum += (3 + T_EXTRA(info ->InputFormbt)) * sizfof(dmsFlobt32Numbfr);
        rfturn bddum;
    }
}

// Tiis is b donvfrsion of XYZ doublf to 16 bits
stbtid
dmsUInt8Numbfr* UnrollXYZDoublfTo16(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wIn[],
                                    rfgistfr dmsUInt8Numbfr* bddum,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    if (T_PLANAR(info -> InputFormbt)) {

        dmsFlobt64Numbfr* Pt = (dmsFlobt64Numbfr*) bddum;
        dmsCIEXYZ XYZ;

        XYZ.X = Pt[0];
        XYZ.Y = Pt[Stridf];
        XYZ.Z = Pt[Stridf*2];
        dmsFlobt2XYZEndodfd(wIn, &XYZ);

        rfturn bddum + sizfof(dmsFlobt64Numbfr);

    }

    flsf {
        dmsFlobt2XYZEndodfd(wIn, (dmsCIEXYZ*) bddum);
        bddum += sizfof(dmsCIEXYZ) + T_EXTRA(info ->InputFormbt) * sizfof(dmsFlobt64Numbfr);

        rfturn bddum;
    }
}

// Cifdk if spbdf is mbrkfd bs ink
dmsINLINE dmsBool IsInkSpbdf(dmsUInt32Numbfr Typf)
{
    switdi (T_COLORSPACE(Typf)) {

     dbsf PT_CMY:
     dbsf PT_CMYK:
     dbsf PT_MCH5:
     dbsf PT_MCH6:
     dbsf PT_MCH7:
     dbsf PT_MCH8:
     dbsf PT_MCH9:
     dbsf PT_MCH10:
     dbsf PT_MCH11:
     dbsf PT_MCH12:
     dbsf PT_MCH13:
     dbsf PT_MCH14:
     dbsf PT_MCH15: rfturn TRUE;

     dffbult: rfturn FALSE;
    }
}

// Inks dofs domf in pfrdfntbgf, rfmbining dbsfs brf bftwffn 0..1.0, bgbin to 16 bits
stbtid
dmsUInt8Numbfr* UnrollDoublfTo16(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wIn[],
                                rfgistfr dmsUInt8Numbfr* bddum,
                                rfgistfr dmsUInt32Numbfr Stridf)
{

    int nCibn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    dmsFlobt64Numbfr v;
    dmsUInt16Numbfr  vi;
    int i, stbrt = 0;
   dmsFlobt64Numbfr mbximum = IsInkSpbdf(info ->InputFormbt) ? 655.35 : 65535.0;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        if (Plbnbr)
            v = (dmsFlobt32Numbfr) ((dmsFlobt64Numbfr*) bddum)[(i + stbrt) * Stridf];
        flsf
            v = (dmsFlobt32Numbfr) ((dmsFlobt64Numbfr*) bddum)[i + stbrt];

        vi = _dmsQuidkSbturbtfWord(v * mbximum);

        if (Rfvfrsf)
            vi = REVERSE_FLAVOR_16(vi);

        wIn[indfx] = vi;
    }


    if (Extrb == 0 && SwbpFirst) {
        dmsUInt16Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsUInt16Numbfr));
        wIn[nCibn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        rfturn bddum + sizfof(dmsFlobt64Numbfr);
    flsf
        rfturn bddum + (nCibn + Extrb) * sizfof(dmsFlobt64Numbfr);
}



stbtid
dmsUInt8Numbfr* UnrollFlobtTo16(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wIn[],
                                rfgistfr dmsUInt8Numbfr* bddum,
                                rfgistfr dmsUInt32Numbfr Stridf)
{

    int nCibn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    dmsFlobt32Numbfr v;
    dmsUInt16Numbfr  vi;
    int i, stbrt = 0;
   dmsFlobt64Numbfr mbximum = IsInkSpbdf(info ->InputFormbt) ? 655.35 : 65535.0;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        if (Plbnbr)
            v = (dmsFlobt32Numbfr) ((dmsFlobt32Numbfr*) bddum)[(i + stbrt) * Stridf];
        flsf
            v = (dmsFlobt32Numbfr) ((dmsFlobt32Numbfr*) bddum)[i + stbrt];

        vi = _dmsQuidkSbturbtfWord(v * mbximum);

        if (Rfvfrsf)
            vi = REVERSE_FLAVOR_16(vi);

        wIn[indfx] = vi;
    }


    if (Extrb == 0 && SwbpFirst) {
        dmsUInt16Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsUInt16Numbfr));
        wIn[nCibn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        rfturn bddum + sizfof(dmsFlobt32Numbfr);
    flsf
        rfturn bddum + (nCibn + Extrb) * sizfof(dmsFlobt32Numbfr);
}




// For 1 dibnnfl, wf nffd to duplidbtf dbtb (it domfs in 0..1.0 rbngf)
stbtid
dmsUInt8Numbfr* UnrollDoublf1Cibn(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wIn[],
                                  rfgistfr dmsUInt8Numbfr* bddum,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    dmsFlobt64Numbfr* Inks = (dmsFlobt64Numbfr*) bddum;

    wIn[0] = wIn[1] = wIn[2] = _dmsQuidkSbturbtfWord(Inks[0] * 65535.0);

    rfturn bddum + sizfof(dmsFlobt64Numbfr);

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

//-------------------------------------------------------------------------------------------------------------------

// For bnytiing going from dmsFlobt32Numbfr
stbtid
dmsUInt8Numbfr* UnrollFlobtsToFlobt(_dmsTRANSFORM* info,
                                    dmsFlobt32Numbfr wIn[],
                                    dmsUInt8Numbfr* bddum,
                                    dmsUInt32Numbfr Stridf)
{

    int nCibn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    dmsFlobt32Numbfr v;
    int i, stbrt = 0;
    dmsFlobt32Numbfr mbximum = IsInkSpbdf(info ->InputFormbt) ? 100.0F : 1.0F;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        if (Plbnbr)
            v = (dmsFlobt32Numbfr) ((dmsFlobt32Numbfr*) bddum)[(i + stbrt) * Stridf];
        flsf
            v = (dmsFlobt32Numbfr) ((dmsFlobt32Numbfr*) bddum)[i + stbrt];

        v /= mbximum;

        wIn[indfx] = Rfvfrsf ? 1 - v : v;
    }


    if (Extrb == 0 && SwbpFirst) {
        dmsFlobt32Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsFlobt32Numbfr));
        wIn[nCibn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        rfturn bddum + sizfof(dmsFlobt32Numbfr);
    flsf
        rfturn bddum + (nCibn + Extrb) * sizfof(dmsFlobt32Numbfr);
}

// For bnytiing going from doublf

stbtid
dmsUInt8Numbfr* UnrollDoublfsToFlobt(_dmsTRANSFORM* info,
                                    dmsFlobt32Numbfr wIn[],
                                    dmsUInt8Numbfr* bddum,
                                    dmsUInt32Numbfr Stridf)
{

    int nCibn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    dmsFlobt64Numbfr v;
    int i, stbrt = 0;
    dmsFlobt64Numbfr mbximum = IsInkSpbdf(info ->InputFormbt) ? 100.0 : 1.0;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        if (Plbnbr)
            v = (dmsFlobt64Numbfr) ((dmsFlobt64Numbfr*) bddum)[(i + stbrt)  * Stridf];
        flsf
            v = (dmsFlobt64Numbfr) ((dmsFlobt64Numbfr*) bddum)[i + stbrt];

        v /= mbximum;

        wIn[indfx] = (dmsFlobt32Numbfr) (Rfvfrsf ? 1.0 - v : v);
    }


    if (Extrb == 0 && SwbpFirst) {
        dmsFlobt32Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsFlobt32Numbfr));
        wIn[nCibn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        rfturn bddum + sizfof(dmsFlobt64Numbfr);
    flsf
        rfturn bddum + (nCibn + Extrb) * sizfof(dmsFlobt64Numbfr);
}



// From Lbb doublf to dmsFlobt32Numbfr
stbtid
dmsUInt8Numbfr* UnrollLbbDoublfToFlobt(_dmsTRANSFORM* info,
                                       dmsFlobt32Numbfr wIn[],
                                       dmsUInt8Numbfr* bddum,
                                       dmsUInt32Numbfr Stridf)
{
    dmsFlobt64Numbfr* Pt = (dmsFlobt64Numbfr*) bddum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / 100.0);                            // from 0..100 to 0..1
        wIn[1] = (dmsFlobt32Numbfr) ((Pt[Stridf] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (dmsFlobt32Numbfr) ((Pt[Stridf*2] + 128) / 255.0);

        rfturn bddum + sizfof(dmsFlobt64Numbfr);
    }
    flsf {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / 100.0);            // from 0..100 to 0..1
        wIn[1] = (dmsFlobt32Numbfr) ((Pt[1] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (dmsFlobt32Numbfr) ((Pt[2] + 128) / 255.0);

        bddum += sizfof(dmsFlobt64Numbfr)*(3 + T_EXTRA(info ->InputFormbt));
        rfturn bddum;
    }
}

// From Lbb doublf to dmsFlobt32Numbfr
stbtid
dmsUInt8Numbfr* UnrollLbbFlobtToFlobt(_dmsTRANSFORM* info,
                                      dmsFlobt32Numbfr wIn[],
                                      dmsUInt8Numbfr* bddum,
                                      dmsUInt32Numbfr Stridf)
{
    dmsFlobt32Numbfr* Pt = (dmsFlobt32Numbfr*) bddum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / 100.0);                 // from 0..100 to 0..1
        wIn[1] = (dmsFlobt32Numbfr) ((Pt[Stridf] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (dmsFlobt32Numbfr) ((Pt[Stridf*2] + 128) / 255.0);

        rfturn bddum + sizfof(dmsFlobt32Numbfr);
    }
    flsf {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / 100.0);            // from 0..100 to 0..1
        wIn[1] = (dmsFlobt32Numbfr) ((Pt[1] + 128) / 255.0);    // form -128..+127 to 0..1
        wIn[2] = (dmsFlobt32Numbfr) ((Pt[2] + 128) / 255.0);

        bddum += sizfof(dmsFlobt32Numbfr)*(3 + T_EXTRA(info ->InputFormbt));
        rfturn bddum;
    }
}



// 1.15 fixfd point, tibt mfbns mbximum vbluf is MAX_ENCODEABLE_XYZ (0xFFFF)
stbtid
dmsUInt8Numbfr* UnrollXYZDoublfToFlobt(_dmsTRANSFORM* info,
                                       dmsFlobt32Numbfr wIn[],
                                       dmsUInt8Numbfr* bddum,
                                       dmsUInt32Numbfr Stridf)
{
    dmsFlobt64Numbfr* Pt = (dmsFlobt64Numbfr*) bddum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (dmsFlobt32Numbfr) (Pt[Stridf] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (dmsFlobt32Numbfr) (Pt[Stridf*2] / MAX_ENCODEABLE_XYZ);

        rfturn bddum + sizfof(dmsFlobt64Numbfr);
    }
    flsf {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (dmsFlobt32Numbfr) (Pt[1] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (dmsFlobt32Numbfr) (Pt[2] / MAX_ENCODEABLE_XYZ);

        bddum += sizfof(dmsFlobt64Numbfr)*(3 + T_EXTRA(info ->InputFormbt));
        rfturn bddum;
    }
}

stbtid
dmsUInt8Numbfr* UnrollXYZFlobtToFlobt(_dmsTRANSFORM* info,
                                      dmsFlobt32Numbfr wIn[],
                                      dmsUInt8Numbfr* bddum,
                                      dmsUInt32Numbfr Stridf)
{
    dmsFlobt32Numbfr* Pt = (dmsFlobt32Numbfr*) bddum;

    if (T_PLANAR(info -> InputFormbt)) {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (dmsFlobt32Numbfr) (Pt[Stridf] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (dmsFlobt32Numbfr) (Pt[Stridf*2] / MAX_ENCODEABLE_XYZ);

        rfturn bddum + sizfof(dmsFlobt32Numbfr);
    }
    flsf {

        wIn[0] = (dmsFlobt32Numbfr) (Pt[0] / MAX_ENCODEABLE_XYZ);
        wIn[1] = (dmsFlobt32Numbfr) (Pt[1] / MAX_ENCODEABLE_XYZ);
        wIn[2] = (dmsFlobt32Numbfr) (Pt[2] / MAX_ENCODEABLE_XYZ);

        bddum += sizfof(dmsFlobt32Numbfr)*(3 + T_EXTRA(info ->InputFormbt));
        rfturn bddum;
    }
}



// Pbdking routinfs -----------------------------------------------------------------------------------------------------------


// Gfnfrid diunky for bytf

stbtid
dmsUInt8Numbfr* PbdkAnyBytfs(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wOut[],
                             rfgistfr dmsUInt8Numbfr* output,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsUInt8Numbfr* swbp1;
    dmsUInt8Numbfr v = 0;
    int i;

    swbp1 = output;

    if (ExtrbFirst) {
        output += Extrb;
    }

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = FROM_16_TO_8(wOut[indfx]);

        if (Rfvfrsf)
            v = REVERSE_FLAVOR_8(v);

        *output++ = v;
    }

    if (!ExtrbFirst) {
        output += Extrb;
    }

    if (Extrb == 0 && SwbpFirst) {

        mfmmovf(swbp1 + 1, swbp1, nCibn-1);
        *swbp1 = v;
    }


    rfturn output;

    dmsUNUSED_PARAMETER(Stridf);
}



stbtid
dmsUInt8Numbfr* PbdkAnyWords(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wOut[],
                             rfgistfr dmsUInt8Numbfr* output,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int SwbpEndibn = T_ENDIAN16(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsUInt16Numbfr* swbp1;
    dmsUInt16Numbfr v = 0;
    int i;

    swbp1 = (dmsUInt16Numbfr*) output;

    if (ExtrbFirst) {
        output += Extrb * sizfof(dmsUInt16Numbfr);
    }

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = wOut[indfx];

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        if (Rfvfrsf)
            v = REVERSE_FLAVOR_16(v);

        *(dmsUInt16Numbfr*) output = v;

        output += sizfof(dmsUInt16Numbfr);
    }

    if (!ExtrbFirst) {
        output += Extrb * sizfof(dmsUInt16Numbfr);
    }

    if (Extrb == 0 && SwbpFirst) {

        mfmmovf(swbp1 + 1, swbp1, (nCibn-1)* sizfof(dmsUInt16Numbfr));
        *swbp1 = v;
    }


    rfturn output;

    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* PbdkPlbnbrBytfs(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wOut[],
                                rfgistfr dmsUInt8Numbfr* output,
                                rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn     = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp    = T_DOSWAP(info ->OutputFormbt);
    int SwbpFirst = T_SWAPFIRST(info ->OutputFormbt);
    int Rfvfrsf   = T_FLAVOR(info ->OutputFormbt);
    int i;
    dmsUInt8Numbfr* Init = output;


    if (DoSwbp ^ SwbpFirst) {
        output += T_EXTRA(info -> OutputFormbt) * Stridf;
    }


    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;
        dmsUInt8Numbfr v = FROM_16_TO_8(wOut[indfx]);

        *(dmsUInt8Numbfr*)  output = (dmsUInt8Numbfr) (Rfvfrsf ? REVERSE_FLAVOR_8(v) : v);
        output += Stridf;
    }

    rfturn (Init + 1);

    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* PbdkPlbnbrWords(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wOut[],
                                rfgistfr dmsUInt8Numbfr* output,
                                rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf= T_FLAVOR(info ->OutputFormbt);
    int SwbpEndibn = T_ENDIAN16(info -> OutputFormbt);
    int i;
    dmsUInt8Numbfr* Init = output;
    dmsUInt16Numbfr v;

    if (DoSwbp) {
        output += T_EXTRA(info -> OutputFormbt) * Stridf * sizfof(dmsUInt16Numbfr);
    }

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = wOut[indfx];

        if (SwbpEndibn)
            v = CHANGE_ENDIAN(v);

        if (Rfvfrsf)
            v =  REVERSE_FLAVOR_16(v);

        *(dmsUInt16Numbfr*) output = v;
        output += (Stridf * sizfof(dmsUInt16Numbfr));
    }

    rfturn (Init + sizfof(dmsUInt16Numbfr));
}

// CMYKdm (unrollfd for spffd)

stbtid
dmsUInt8Numbfr* Pbdk6Bytfs(rfgistfr _dmsTRANSFORM* info,
                           rfgistfr dmsUInt16Numbfr wOut[],
                           rfgistfr dmsUInt8Numbfr* output,
                           rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[4]);
    *output++ = FROM_16_TO_8(wOut[5]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// KCMYdm

stbtid
dmsUInt8Numbfr* Pbdk6BytfsSwbp(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[5]);
    *output++ = FROM_16_TO_8(wOut[4]);
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// CMYKdm
stbtid
dmsUInt8Numbfr* Pbdk6Words(rfgistfr _dmsTRANSFORM* info,
                           rfgistfr dmsUInt16Numbfr wOut[],
                           rfgistfr dmsUInt8Numbfr* output,
                           rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[3];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[4];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[5];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// KCMYdm
stbtid
dmsUInt8Numbfr* Pbdk6WordsSwbp(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[5];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[4];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[3];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk4Bytfs(rfgistfr _dmsTRANSFORM* info,
                           rfgistfr dmsUInt16Numbfr wOut[],
                           rfgistfr dmsUInt8Numbfr* output,
                           rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[3]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk4BytfsRfvfrsf(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wOut[],
                                  rfgistfr dmsUInt8Numbfr* output,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[0]));
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[1]));
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[2]));
    *output++ = REVERSE_FLAVOR_8(FROM_16_TO_8(wOut[3]));

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk4BytfsSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wOut[],
                                    rfgistfr dmsUInt8Numbfr* output,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// ABGR
stbtid
dmsUInt8Numbfr* Pbdk4BytfsSwbp(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[3]);
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk4BytfsSwbpSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                        rfgistfr dmsUInt16Numbfr wOut[],
                                        rfgistfr dmsUInt8Numbfr* output,
                                        rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[3]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk4Words(rfgistfr _dmsTRANSFORM* info,
                           rfgistfr dmsUInt16Numbfr wOut[],
                           rfgistfr dmsUInt8Numbfr* output,
                           rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[3];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk4WordsRfvfrsf(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wOut[],
                                  rfgistfr dmsUInt8Numbfr* output,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = REVERSE_FLAVOR_16(wOut[0]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = REVERSE_FLAVOR_16(wOut[1]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = REVERSE_FLAVOR_16(wOut[2]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = REVERSE_FLAVOR_16(wOut[3]);
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// ABGR
stbtid
dmsUInt8Numbfr* Pbdk4WordsSwbp(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[3];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

// CMYK
stbtid
dmsUInt8Numbfr* Pbdk4WordsBigEndibn(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wOut[],
                                    rfgistfr dmsUInt8Numbfr* output,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[0]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[1]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[2]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[3]);
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* PbdkLbbV2_8(rfgistfr _dmsTRANSFORM* info,
                            rfgistfr dmsUInt16Numbfr wOut[],
                            rfgistfr dmsUInt8Numbfr* output,
                            rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[0]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[1]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[2]));

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* PbdkALbbV2_8(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wOut[],
                             rfgistfr dmsUInt8Numbfr* output,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    output++;
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[0]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[1]));
    *output++ = FROM_16_TO_8(FomLbbV4ToLbbV2(wOut[2]));

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* PbdkLbbV2_16(rfgistfr _dmsTRANSFORM* info,
                             rfgistfr dmsUInt16Numbfr wOut[],
                             rfgistfr dmsUInt8Numbfr* output,
                             rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = FomLbbV4ToLbbV2(wOut[0]);
    output += 2;
    *(dmsUInt16Numbfr*) output = FomLbbV4ToLbbV2(wOut[1]);
    output += 2;
    *(dmsUInt16Numbfr*) output = FomLbbV4ToLbbV2(wOut[2]);
    output += 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3Bytfs(rfgistfr _dmsTRANSFORM* info,
                           rfgistfr dmsUInt16Numbfr wOut[],
                           rfgistfr dmsUInt8Numbfr* output,
                           rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsOptimizfd(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wOut[],
                                    rfgistfr dmsUInt8Numbfr* output,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = (wOut[0] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[2] & 0xFF);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsSwbp(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsSwbpOptimizfd(rfgistfr _dmsTRANSFORM* info,
                                        rfgistfr dmsUInt16Numbfr wOut[],
                                        rfgistfr dmsUInt8Numbfr* output,
                                        rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = (wOut[2] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[0] & 0xFF);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk3Words(rfgistfr _dmsTRANSFORM* info,
                           rfgistfr dmsUInt16Numbfr wOut[],
                           rfgistfr dmsUInt8Numbfr* output,
                           rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3WordsSwbp(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3WordsBigEndibn(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wOut[],
                                    rfgistfr dmsUInt8Numbfr* output,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[0]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[1]);
    output+= 2;
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[2]);
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1(rfgistfr _dmsTRANSFORM* info,
                                   rfgistfr dmsUInt16Numbfr wOut[],
                                   rfgistfr dmsUInt8Numbfr* output,
                                   rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);
    output++;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1Optimizfd(rfgistfr _dmsTRANSFORM* info,
                                            rfgistfr dmsUInt16Numbfr wOut[],
                                            rfgistfr dmsUInt8Numbfr* output,
                                            rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = (wOut[0] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[2] & 0xFF);
    output++;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1SwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                            rfgistfr dmsUInt16Numbfr wOut[],
                                            rfgistfr dmsUInt8Numbfr* output,
                                            rfgistfr dmsUInt32Numbfr Stridf)
{
    output++;
    *output++ = FROM_16_TO_8(wOut[0]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[2]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1SwbpFirstOptimizfd(rfgistfr _dmsTRANSFORM* info,
                                                     rfgistfr dmsUInt16Numbfr wOut[],
                                                     rfgistfr dmsUInt8Numbfr* output,
                                                     rfgistfr dmsUInt32Numbfr Stridf)
{
    output++;
    *output++ = (wOut[0] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[2] & 0xFF);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1Swbp(rfgistfr _dmsTRANSFORM* info,
                                       rfgistfr dmsUInt16Numbfr wOut[],
                                       rfgistfr dmsUInt8Numbfr* output,
                                       rfgistfr dmsUInt32Numbfr Stridf)
{
    output++;
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1SwbpOptimizfd(rfgistfr _dmsTRANSFORM* info,
                                                rfgistfr dmsUInt16Numbfr wOut[],
                                                rfgistfr dmsUInt8Numbfr* output,
                                                rfgistfr dmsUInt32Numbfr Stridf)
{
    output++;
    *output++ = (wOut[2] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[0] & 0xFF);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1SwbpSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                                rfgistfr dmsUInt16Numbfr wOut[],
                                                rfgistfr dmsUInt8Numbfr* output,
                                                rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[2]);
    *output++ = FROM_16_TO_8(wOut[1]);
    *output++ = FROM_16_TO_8(wOut[0]);
    output++;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3BytfsAndSkip1SwbpSwbpFirstOptimizfd(rfgistfr _dmsTRANSFORM* info,
                                                         rfgistfr dmsUInt16Numbfr wOut[],
                                                         rfgistfr dmsUInt8Numbfr* output,
                                                         rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = (wOut[2] & 0xFF);
    *output++ = (wOut[1] & 0xFF);
    *output++ = (wOut[0] & 0xFF);
    output++;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3WordsAndSkip1(rfgistfr _dmsTRANSFORM* info,
                                   rfgistfr dmsUInt16Numbfr wOut[],
                                   rfgistfr dmsUInt8Numbfr* output,
                                   rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk3WordsAndSkip1Swbp(rfgistfr _dmsTRANSFORM* info,
                                       rfgistfr dmsUInt16Numbfr wOut[],
                                       rfgistfr dmsUInt8Numbfr* output,
                                       rfgistfr dmsUInt32Numbfr Stridf)
{
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk3WordsAndSkip1SwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                            rfgistfr dmsUInt16Numbfr wOut[],
                                            rfgistfr dmsUInt8Numbfr* output,
                                            rfgistfr dmsUInt32Numbfr Stridf)
{
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk3WordsAndSkip1SwbpSwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                                rfgistfr dmsUInt16Numbfr wOut[],
                                                rfgistfr dmsUInt8Numbfr* output,
                                                rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[2];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[1];
    output+= 2;
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}



stbtid
dmsUInt8Numbfr* Pbdk1Bytf(rfgistfr _dmsTRANSFORM* info,
                          rfgistfr dmsUInt16Numbfr wOut[],
                          rfgistfr dmsUInt8Numbfr* output,
                          rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[0]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk1BytfRfvfrsfd(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wOut[],
                                  rfgistfr dmsUInt8Numbfr* output,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(REVERSE_FLAVOR_16(wOut[0]));

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk1BytfSkip1(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *output++ = FROM_16_TO_8(wOut[0]);
    output++;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk1BytfSkip1SwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                        rfgistfr dmsUInt16Numbfr wOut[],
                                        rfgistfr dmsUInt8Numbfr* output,
                                        rfgistfr dmsUInt32Numbfr Stridf)
{
    output++;
    *output++ = FROM_16_TO_8(wOut[0]);

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk1Word(rfgistfr _dmsTRANSFORM* info,
                          rfgistfr dmsUInt16Numbfr wOut[],
                          rfgistfr dmsUInt8Numbfr* output,
                          rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk1WordRfvfrsfd(rfgistfr _dmsTRANSFORM* info,
                                  rfgistfr dmsUInt16Numbfr wOut[],
                                  rfgistfr dmsUInt8Numbfr* output,
                                  rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = REVERSE_FLAVOR_16(wOut[0]);
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk1WordBigEndibn(rfgistfr _dmsTRANSFORM* info,
                                   rfgistfr dmsUInt16Numbfr wOut[],
                                   rfgistfr dmsUInt8Numbfr* output,
                                   rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = CHANGE_ENDIAN(wOut[0]);
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


stbtid
dmsUInt8Numbfr* Pbdk1WordSkip1(rfgistfr _dmsTRANSFORM* info,
                               rfgistfr dmsUInt16Numbfr wOut[],
                               rfgistfr dmsUInt8Numbfr* output,
                               rfgistfr dmsUInt32Numbfr Stridf)
{
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 4;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}

stbtid
dmsUInt8Numbfr* Pbdk1WordSkip1SwbpFirst(rfgistfr _dmsTRANSFORM* info,
                                        rfgistfr dmsUInt16Numbfr wOut[],
                                        rfgistfr dmsUInt8Numbfr* output,
                                        rfgistfr dmsUInt32Numbfr Stridf)
{
    output += 2;
    *(dmsUInt16Numbfr*) output = wOut[0];
    output+= 2;

    rfturn output;

    dmsUNUSED_PARAMETER(info);
    dmsUNUSED_PARAMETER(Stridf);
}


// Unfndodfd Flobt vblufs -- don't try optimizf spffd
stbtid
dmsUInt8Numbfr* PbdkLbbDoublfFrom16(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wOut[],
                                    rfgistfr dmsUInt8Numbfr* output,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{

    if (T_PLANAR(info -> OutputFormbt)) {

        dmsCIELbb  Lbb;
        dmsFlobt64Numbfr* Out = (dmsFlobt64Numbfr*) output;
        dmsLbbEndodfd2Flobt(&Lbb, wOut);

        Out[0]        = Lbb.L;
        Out[Stridf]   = Lbb.b;
        Out[Stridf*2] = Lbb.b;

        rfturn output + sizfof(dmsFlobt64Numbfr);
    }
    flsf {

        dmsLbbEndodfd2Flobt((dmsCIELbb*) output, wOut);
        rfturn output + (sizfof(dmsCIELbb) + T_EXTRA(info ->OutputFormbt) * sizfof(dmsFlobt64Numbfr));
    }
}


stbtid
dmsUInt8Numbfr* PbdkLbbFlobtFrom16(rfgistfr _dmsTRANSFORM* info,
                                    rfgistfr dmsUInt16Numbfr wOut[],
                                    rfgistfr dmsUInt8Numbfr* output,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    dmsCIELbb  Lbb;
    dmsLbbEndodfd2Flobt(&Lbb, wOut);

    if (T_PLANAR(info -> OutputFormbt)) {

        dmsFlobt32Numbfr* Out = (dmsFlobt32Numbfr*) output;

        Out[0]        = (dmsFlobt32Numbfr)Lbb.L;
        Out[Stridf]   = (dmsFlobt32Numbfr)Lbb.b;
        Out[Stridf*2] = (dmsFlobt32Numbfr)Lbb.b;

        rfturn output + sizfof(dmsFlobt32Numbfr);
    }
    flsf {

       ((dmsFlobt32Numbfr*) output)[0] = (dmsFlobt32Numbfr) Lbb.L;
       ((dmsFlobt32Numbfr*) output)[1] = (dmsFlobt32Numbfr) Lbb.b;
       ((dmsFlobt32Numbfr*) output)[2] = (dmsFlobt32Numbfr) Lbb.b;

        rfturn output + (3 + T_EXTRA(info ->OutputFormbt)) * sizfof(dmsFlobt32Numbfr);
    }
}

stbtid
dmsUInt8Numbfr* PbdkXYZDoublfFrom16(rfgistfr _dmsTRANSFORM* Info,
                                    rfgistfr dmsUInt16Numbfr wOut[],
                                    rfgistfr dmsUInt8Numbfr* output,
                                    rfgistfr dmsUInt32Numbfr Stridf)
{
    if (T_PLANAR(Info -> OutputFormbt)) {

        dmsCIEXYZ XYZ;
        dmsFlobt64Numbfr* Out = (dmsFlobt64Numbfr*) output;
        dmsXYZEndodfd2Flobt(&XYZ, wOut);

        Out[0]        = XYZ.X;
        Out[Stridf]   = XYZ.Y;
        Out[Stridf*2] = XYZ.Z;

        rfturn output + sizfof(dmsFlobt64Numbfr);

    }
    flsf {

        dmsXYZEndodfd2Flobt((dmsCIEXYZ*) output, wOut);

        rfturn output + (sizfof(dmsCIEXYZ) + T_EXTRA(Info ->OutputFormbt) * sizfof(dmsFlobt64Numbfr));
    }
}

stbtid
dmsUInt8Numbfr* PbdkDoublfFrom16(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wOut[],
                                rfgistfr dmsUInt8Numbfr* output,
                                rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsFlobt64Numbfr mbximum = IsInkSpbdf(info ->OutputFormbt) ? 655.35 : 65535.0;
    dmsFlobt64Numbfr v = 0;
    dmsFlobt64Numbfr* swbp1 = (dmsFlobt64Numbfr*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = (dmsFlobt64Numbfr) wOut[indfx] / mbximum;

        if (Rfvfrsf)
            v = mbximum - v;

        if (Plbnbr)
            ((dmsFlobt64Numbfr*) output)[(i + stbrt)  * Stridf]= v;
        flsf
            ((dmsFlobt64Numbfr*) output)[i + stbrt] = v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizfof(dmsFlobt64Numbfr);
    }

    if (Extrb == 0 && SwbpFirst) {

         mfmmovf(swbp1 + 1, swbp1, (nCibn-1)* sizfof(dmsFlobt64Numbfr));
        *swbp1 = v;
    }

    if (T_PLANAR(info -> OutputFormbt))
        rfturn output + sizfof(dmsFlobt64Numbfr);
    flsf
        rfturn output + nCibn * sizfof(dmsFlobt64Numbfr);

}


stbtid
dmsUInt8Numbfr* PbdkFlobtFrom16(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wOut[],
                                rfgistfr dmsUInt8Numbfr* output,
                                rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsFlobt64Numbfr mbximum = IsInkSpbdf(info ->OutputFormbt) ? 655.35 : 65535.0;
    dmsFlobt64Numbfr v = 0;
    dmsFlobt32Numbfr* swbp1 = (dmsFlobt32Numbfr*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = (dmsFlobt64Numbfr) wOut[indfx] / mbximum;

        if (Rfvfrsf)
            v = mbximum - v;

        if (Plbnbr)
            ((dmsFlobt32Numbfr*) output)[(i + stbrt ) * Stridf]= (dmsFlobt32Numbfr) v;
        flsf
            ((dmsFlobt32Numbfr*) output)[i + stbrt] = (dmsFlobt32Numbfr) v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizfof(dmsFlobt32Numbfr);
    }

  if (Extrb == 0 && SwbpFirst) {

         mfmmovf(swbp1 + 1, swbp1, (nCibn-1)* sizfof(dmsFlobt32Numbfr));
        *swbp1 = (dmsFlobt32Numbfr) v;
    }

    if (T_PLANAR(info -> OutputFormbt))
        rfturn output + sizfof(dmsFlobt32Numbfr);
    flsf
        rfturn output + nCibn * sizfof(dmsFlobt32Numbfr);
}



// --------------------------------------------------------------------------------------------------------

stbtid
dmsUInt8Numbfr* PbdkFlobtsFromFlobt(_dmsTRANSFORM* info,
                                    dmsFlobt32Numbfr wOut[],
                                    dmsUInt8Numbfr* output,
                                    dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsFlobt64Numbfr mbximum = IsInkSpbdf(info ->OutputFormbt) ? 100.0 : 1.0;
    dmsFlobt32Numbfr* swbp1 = (dmsFlobt32Numbfr*) output;
    dmsFlobt64Numbfr v = 0;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = wOut[indfx] * mbximum;

        if (Rfvfrsf)
            v = mbximum - v;

        if (Plbnbr)
            ((dmsFlobt32Numbfr*) output)[(i + stbrt)* Stridf]= (dmsFlobt32Numbfr) v;
        flsf
            ((dmsFlobt32Numbfr*) output)[i + stbrt] = (dmsFlobt32Numbfr) v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizfof(dmsFlobt32Numbfr);
    }

   if (Extrb == 0 && SwbpFirst) {

         mfmmovf(swbp1 + 1, swbp1, (nCibn-1)* sizfof(dmsFlobt32Numbfr));
        *swbp1 = (dmsFlobt32Numbfr) v;
    }

    if (T_PLANAR(info -> OutputFormbt))
        rfturn output + sizfof(dmsFlobt32Numbfr);
    flsf
        rfturn output + nCibn * sizfof(dmsFlobt32Numbfr);
}

stbtid
dmsUInt8Numbfr* PbdkDoublfsFromFlobt(_dmsTRANSFORM* info,
                                    dmsFlobt32Numbfr wOut[],
                                    dmsUInt8Numbfr* output,
                                    dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsFlobt64Numbfr mbximum = IsInkSpbdf(info ->OutputFormbt) ? 100.0 : 1.0;
    dmsFlobt64Numbfr v = 0;
    dmsFlobt64Numbfr* swbp1 = (dmsFlobt64Numbfr*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = wOut[indfx] * mbximum;

        if (Rfvfrsf)
            v = mbximum - v;

        if (Plbnbr)
            ((dmsFlobt64Numbfr*) output)[(i + stbrt) * Stridf] =  v;
        flsf
            ((dmsFlobt64Numbfr*) output)[i + stbrt] =  v;
    }

    if (!ExtrbFirst) {
        output += Extrb * sizfof(dmsFlobt64Numbfr);
    }

   if (Extrb == 0 && SwbpFirst) {

         mfmmovf(swbp1 + 1, swbp1, (nCibn-1)* sizfof(dmsFlobt64Numbfr));
        *swbp1 = v;
    }


    if (T_PLANAR(info -> OutputFormbt))
        rfturn output + sizfof(dmsFlobt64Numbfr);
    flsf
        rfturn output + nCibn * sizfof(dmsFlobt64Numbfr);

}





stbtid
dmsUInt8Numbfr* PbdkLbbFlobtFromFlobt(_dmsTRANSFORM* Info,
                                      dmsFlobt32Numbfr wOut[],
                                      dmsUInt8Numbfr* output,
                                      dmsUInt32Numbfr Stridf)
{
    dmsFlobt32Numbfr* Out = (dmsFlobt32Numbfr*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (dmsFlobt32Numbfr) (wOut[0] * 100.0);
        Out[Stridf]   = (dmsFlobt32Numbfr) (wOut[1] * 255.0 - 128.0);
        Out[Stridf*2] = (dmsFlobt32Numbfr) (wOut[2] * 255.0 - 128.0);

        rfturn output + sizfof(dmsFlobt32Numbfr);
    }
    flsf {

        Out[0] = (dmsFlobt32Numbfr) (wOut[0] * 100.0);
        Out[1] = (dmsFlobt32Numbfr) (wOut[1] * 255.0 - 128.0);
        Out[2] = (dmsFlobt32Numbfr) (wOut[2] * 255.0 - 128.0);

        rfturn output + (sizfof(dmsFlobt32Numbfr)*3 + T_EXTRA(Info ->OutputFormbt) * sizfof(dmsFlobt32Numbfr));
    }

}


stbtid
dmsUInt8Numbfr* PbdkLbbDoublfFromFlobt(_dmsTRANSFORM* Info,
                                       dmsFlobt32Numbfr wOut[],
                                       dmsUInt8Numbfr* output,
                                       dmsUInt32Numbfr Stridf)
{
    dmsFlobt64Numbfr* Out = (dmsFlobt64Numbfr*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (dmsFlobt64Numbfr) (wOut[0] * 100.0);
        Out[Stridf]   = (dmsFlobt64Numbfr) (wOut[1] * 255.0 - 128.0);
        Out[Stridf*2] = (dmsFlobt64Numbfr) (wOut[2] * 255.0 - 128.0);

        rfturn output + sizfof(dmsFlobt64Numbfr);
    }
    flsf {

        Out[0] = (dmsFlobt64Numbfr) (wOut[0] * 100.0);
        Out[1] = (dmsFlobt64Numbfr) (wOut[1] * 255.0 - 128.0);
        Out[2] = (dmsFlobt64Numbfr) (wOut[2] * 255.0 - 128.0);

        rfturn output + (sizfof(dmsFlobt64Numbfr)*3 + T_EXTRA(Info ->OutputFormbt) * sizfof(dmsFlobt64Numbfr));
    }

}


// From 0..1 rbngf to 0..MAX_ENCODEABLE_XYZ
stbtid
dmsUInt8Numbfr* PbdkXYZFlobtFromFlobt(_dmsTRANSFORM* Info,
                                      dmsFlobt32Numbfr wOut[],
                                      dmsUInt8Numbfr* output,
                                      dmsUInt32Numbfr Stridf)
{
    dmsFlobt32Numbfr* Out = (dmsFlobt32Numbfr*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (dmsFlobt32Numbfr) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[Stridf]   = (dmsFlobt32Numbfr) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[Stridf*2] = (dmsFlobt32Numbfr) (wOut[2] * MAX_ENCODEABLE_XYZ);

        rfturn output + sizfof(dmsFlobt32Numbfr);
    }
    flsf {

        Out[0] = (dmsFlobt32Numbfr) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[1] = (dmsFlobt32Numbfr) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[2] = (dmsFlobt32Numbfr) (wOut[2] * MAX_ENCODEABLE_XYZ);

        rfturn output + (sizfof(dmsFlobt32Numbfr)*3 + T_EXTRA(Info ->OutputFormbt) * sizfof(dmsFlobt32Numbfr));
    }

}

// Sbmf, but donvfrt to doublf
stbtid
dmsUInt8Numbfr* PbdkXYZDoublfFromFlobt(_dmsTRANSFORM* Info,
                                       dmsFlobt32Numbfr wOut[],
                                       dmsUInt8Numbfr* output,
                                       dmsUInt32Numbfr Stridf)
{
    dmsFlobt64Numbfr* Out = (dmsFlobt64Numbfr*) output;

    if (T_PLANAR(Info -> OutputFormbt)) {

        Out[0]        = (dmsFlobt64Numbfr) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[Stridf]   = (dmsFlobt64Numbfr) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[Stridf*2] = (dmsFlobt64Numbfr) (wOut[2] * MAX_ENCODEABLE_XYZ);

        rfturn output + sizfof(dmsFlobt64Numbfr);
    }
    flsf {

        Out[0] = (dmsFlobt64Numbfr) (wOut[0] * MAX_ENCODEABLE_XYZ);
        Out[1] = (dmsFlobt64Numbfr) (wOut[1] * MAX_ENCODEABLE_XYZ);
        Out[2] = (dmsFlobt64Numbfr) (wOut[2] * MAX_ENCODEABLE_XYZ);

        rfturn output + (sizfof(dmsFlobt64Numbfr)*3 + T_EXTRA(Info ->OutputFormbt) * sizfof(dmsFlobt64Numbfr));
    }

}


// ----------------------------------------------------------------------------------------------------------------

#ifndff CMS_NO_HALF_SUPPORT

// Dfdodfs bn strfbm of iblf flobts to wIn[] dfsdribfd by input formbt

stbtid
dmsUInt8Numbfr* UnrollHblfTo16(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wIn[],
                                rfgistfr dmsUInt8Numbfr* bddum,
                                rfgistfr dmsUInt32Numbfr Stridf)
{

    int nCibn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    dmsFlobt32Numbfr v;
    int i, stbrt = 0;
    dmsFlobt32Numbfr mbximum = IsInkSpbdf(info ->InputFormbt) ? 655.35F : 65535.0F;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        if (Plbnbr)
            v = _dmsHblf2Flobt ( ((dmsUInt16Numbfr*) bddum)[(i + stbrt) * Stridf] );
        flsf
            v = _dmsHblf2Flobt ( ((dmsUInt16Numbfr*) bddum)[i + stbrt] ) ;

        if (Rfvfrsf) v = mbximum - v;

        wIn[indfx] = _dmsQuidkSbturbtfWord(v * mbximum);
    }


    if (Extrb == 0 && SwbpFirst) {
        dmsUInt16Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsUInt16Numbfr));
        wIn[nCibn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        rfturn bddum + sizfof(dmsUInt16Numbfr);
    flsf
        rfturn bddum + (nCibn + Extrb) * sizfof(dmsUInt16Numbfr);
}

// Dfdodfs bn strfbm of iblf flobts to wIn[] dfsdribfd by input formbt

stbtid
dmsUInt8Numbfr* UnrollHblfToFlobt(_dmsTRANSFORM* info,
                                    dmsFlobt32Numbfr wIn[],
                                    dmsUInt8Numbfr* bddum,
                                    dmsUInt32Numbfr Stridf)
{

    int nCibn      = T_CHANNELS(info -> InputFormbt);
    int DoSwbp     = T_DOSWAP(info ->InputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->InputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> InputFormbt);
    int Extrb      = T_EXTRA(info -> InputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    int Plbnbr     = T_PLANAR(info -> InputFormbt);
    dmsFlobt32Numbfr v;
    int i, stbrt = 0;
    dmsFlobt32Numbfr mbximum = IsInkSpbdf(info ->InputFormbt) ? 100.0F : 1.0F;


    if (ExtrbFirst)
            stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        if (Plbnbr)
            v =  _dmsHblf2Flobt ( ((dmsUInt16Numbfr*) bddum)[(i + stbrt) * Stridf] );
        flsf
            v =  _dmsHblf2Flobt ( ((dmsUInt16Numbfr*) bddum)[i + stbrt] ) ;

        v /= mbximum;

        wIn[indfx] = Rfvfrsf ? 1 - v : v;
    }


    if (Extrb == 0 && SwbpFirst) {
        dmsFlobt32Numbfr tmp = wIn[0];

        mfmmovf(&wIn[0], &wIn[1], (nCibn-1) * sizfof(dmsFlobt32Numbfr));
        wIn[nCibn-1] = tmp;
    }

    if (T_PLANAR(info -> InputFormbt))
        rfturn bddum + sizfof(dmsUInt16Numbfr);
    flsf
        rfturn bddum + (nCibn + Extrb) * sizfof(dmsUInt16Numbfr);
}


stbtid
dmsUInt8Numbfr* PbdkHblfFrom16(rfgistfr _dmsTRANSFORM* info,
                                rfgistfr dmsUInt16Numbfr wOut[],
                                rfgistfr dmsUInt8Numbfr* output,
                                rfgistfr dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsFlobt32Numbfr mbximum = IsInkSpbdf(info ->OutputFormbt) ? 655.35F : 65535.0F;
    dmsFlobt32Numbfr v = 0;
    dmsUInt16Numbfr* swbp1 = (dmsUInt16Numbfr*) output;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = (dmsFlobt32Numbfr) wOut[indfx] / mbximum;

        if (Rfvfrsf)
            v = mbximum - v;

        if (Plbnbr)
            ((dmsUInt16Numbfr*) output)[(i + stbrt ) * Stridf]= _dmsFlobt2Hblf(v);
        flsf
            ((dmsUInt16Numbfr*) output)[i + stbrt] =  _dmsFlobt2Hblf(v);
    }

    if (!ExtrbFirst) {
        output += Extrb * sizfof(dmsUInt16Numbfr);
    }

  if (Extrb == 0 && SwbpFirst) {

         mfmmovf(swbp1 + 1, swbp1, (nCibn-1)* sizfof(dmsUInt16Numbfr));
        *swbp1 = _dmsFlobt2Hblf(v);
    }

    if (T_PLANAR(info -> OutputFormbt))
        rfturn output + sizfof(dmsUInt16Numbfr);
    flsf
        rfturn output + nCibn * sizfof(dmsUInt16Numbfr);
}



stbtid
dmsUInt8Numbfr* PbdkHblfFromFlobt(_dmsTRANSFORM* info,
                                    dmsFlobt32Numbfr wOut[],
                                    dmsUInt8Numbfr* output,
                                    dmsUInt32Numbfr Stridf)
{
    int nCibn      = T_CHANNELS(info -> OutputFormbt);
    int DoSwbp     = T_DOSWAP(info ->OutputFormbt);
    int Rfvfrsf    = T_FLAVOR(info ->OutputFormbt);
    int Extrb      = T_EXTRA(info -> OutputFormbt);
    int SwbpFirst  = T_SWAPFIRST(info -> OutputFormbt);
    int Plbnbr     = T_PLANAR(info -> OutputFormbt);
    int ExtrbFirst = DoSwbp ^ SwbpFirst;
    dmsFlobt32Numbfr mbximum = IsInkSpbdf(info ->OutputFormbt) ? 100.0F : 1.0F;
    dmsUInt16Numbfr* swbp1 = (dmsUInt16Numbfr*) output;
    dmsFlobt32Numbfr v = 0;
    int i, stbrt = 0;

    if (ExtrbFirst)
        stbrt = Extrb;

    for (i=0; i < nCibn; i++) {

        int indfx = DoSwbp ? (nCibn - i - 1) : i;

        v = wOut[indfx] * mbximum;

        if (Rfvfrsf)
            v = mbximum - v;

        if (Plbnbr)
            ((dmsUInt16Numbfr*) output)[(i + stbrt)* Stridf]= _dmsFlobt2Hblf( v );
        flsf
            ((dmsUInt16Numbfr*) output)[i + stbrt] = _dmsFlobt2Hblf( v );
    }

    if (!ExtrbFirst) {
        output += Extrb * sizfof(dmsUInt16Numbfr);
    }

   if (Extrb == 0 && SwbpFirst) {

         mfmmovf(swbp1 + 1, swbp1, (nCibn-1)* sizfof(dmsUInt16Numbfr));
        *swbp1 = (dmsUInt16Numbfr)  _dmsFlobt2Hblf( v );
    }

    if (T_PLANAR(info -> OutputFormbt))
        rfturn output + sizfof(dmsUInt16Numbfr);
    flsf
        rfturn output + nCibn * sizfof(dmsUInt16Numbfr);
}

#fndif

// ----------------------------------------------------------------------------------------------------------------


stbtid dmsFormbttfrs16 InputFormbttfrs16[] = {

    //    Typf                                          Mbsk                  Fundtion
    //  ----------------------------   ------------------------------------  ----------------------------
    { TYPE_Lbb_DBL,                                 ANYPLANAR|ANYEXTRA,   UnrollLbbDoublfTo16},
    { TYPE_XYZ_DBL,                                 ANYPLANAR|ANYEXTRA,   UnrollXYZDoublfTo16},
    { TYPE_Lbb_FLT,                                 ANYPLANAR|ANYEXTRA,   UnrollLbbFlobtTo16},
    { TYPE_GRAY_DBL,                                                 0,   UnrollDoublf1Cibn},
    { FLOAT_SH(1)|BYTES_SH(0), ANYCHANNELS|ANYPLANAR|ANYSWAPFIRST|ANYFLAVOR|
                                             ANYSWAP|ANYEXTRA|ANYSPACE,   UnrollDoublfTo16},
    { FLOAT_SH(1)|BYTES_SH(4), ANYCHANNELS|ANYPLANAR|ANYSWAPFIRST|ANYFLAVOR|
                                             ANYSWAP|ANYEXTRA|ANYSPACE,   UnrollFlobtTo16},
#ifndff CMS_NO_HALF_SUPPORT
    { FLOAT_SH(1)|BYTES_SH(2), ANYCHANNELS|ANYPLANAR|ANYSWAPFIRST|ANYFLAVOR|
                                            ANYEXTRA|ANYSWAP|ANYSPACE,   UnrollHblfTo16},
#fndif

    { CHANNELS_SH(1)|BYTES_SH(1),                              ANYSPACE,  Unroll1Bytf},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(1),                  ANYSPACE,  Unroll1BytfSkip1},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(2),                  ANYSPACE,  Unroll1BytfSkip2},
    { CHANNELS_SH(1)|BYTES_SH(1)|FLAVOR_SH(1),                 ANYSPACE,  Unroll1BytfRfvfrsfd},
    { COLORSPACE_SH(PT_MCH2)|CHANNELS_SH(2)|BYTES_SH(1),              0,  Unroll2Bytfs},

    { TYPE_LbbV2_8,                                                   0,  UnrollLbbV2_8 },
    { TYPE_ALbbV2_8,                                                  0,  UnrollALbbV2_8 },
    { TYPE_LbbV2_16,                                                  0,  UnrollLbbV2_16 },

    { CHANNELS_SH(3)|BYTES_SH(1),                              ANYSPACE,  Unroll3Bytfs},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1),                 ANYSPACE,  Unroll3BytfsSwbp},
    { CHANNELS_SH(3)|EXTRA_SH(1)|BYTES_SH(1)|DOSWAP_SH(1),     ANYSPACE,  Unroll3BytfsSkip1Swbp},
    { CHANNELS_SH(3)|EXTRA_SH(1)|BYTES_SH(1)|SWAPFIRST_SH(1),  ANYSPACE,  Unroll3BytfsSkip1SwbpFirst},

    { CHANNELS_SH(3)|EXTRA_SH(1)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),
                                                               ANYSPACE,  Unroll3BytfsSkip1SwbpSwbpFirst},

    { CHANNELS_SH(4)|BYTES_SH(1),                              ANYSPACE,  Unroll4Bytfs},
    { CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1),                 ANYSPACE,  Unroll4BytfsRfvfrsf},
    { CHANNELS_SH(4)|BYTES_SH(1)|SWAPFIRST_SH(1),              ANYSPACE,  Unroll4BytfsSwbpFirst},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1),                 ANYSPACE,  Unroll4BytfsSwbp},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1), ANYSPACE,  Unroll4BytfsSwbpSwbpFirst},

    { BYTES_SH(1)|PLANAR_SH(1), ANYFLAVOR|ANYSWAPFIRST|
                                   ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, UnrollPlbnbrBytfs},

    { BYTES_SH(1),    ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                           ANYEXTRA|ANYCHANNELS|ANYSPACE, UnrollCiunkyBytfs},

    { CHANNELS_SH(1)|BYTES_SH(2),                              ANYSPACE,  Unroll1Word},
    { CHANNELS_SH(1)|BYTES_SH(2)|FLAVOR_SH(1),                 ANYSPACE,  Unroll1WordRfvfrsfd},
    { CHANNELS_SH(1)|BYTES_SH(2)|EXTRA_SH(3),                  ANYSPACE,  Unroll1WordSkip3},

    { CHANNELS_SH(2)|BYTES_SH(2),                              ANYSPACE,  Unroll2Words},
    { CHANNELS_SH(3)|BYTES_SH(2),                              ANYSPACE,  Unroll3Words},
    { CHANNELS_SH(4)|BYTES_SH(2),                              ANYSPACE,  Unroll4Words},

    { CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1),                 ANYSPACE,  Unroll3WordsSwbp},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|SWAPFIRST_SH(1),  ANYSPACE,  Unroll3WordsSkip1SwbpFirst},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|DOSWAP_SH(1),     ANYSPACE,  Unroll3WordsSkip1Swbp},
    { CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1),                 ANYSPACE,  Unroll4WordsRfvfrsf},
    { CHANNELS_SH(4)|BYTES_SH(2)|SWAPFIRST_SH(1),              ANYSPACE,  Unroll4WordsSwbpFirst},
    { CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1),                 ANYSPACE,  Unroll4WordsSwbp},
    { CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1)|SWAPFIRST_SH(1), ANYSPACE,  Unroll4WordsSwbpSwbpFirst},


    { BYTES_SH(2)|PLANAR_SH(1),  ANYFLAVOR|ANYSWAP|ANYENDIAN|ANYEXTRA|ANYCHANNELS|ANYSPACE,  UnrollPlbnbrWords},
    { BYTES_SH(2),  ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYENDIAN|ANYEXTRA|ANYCHANNELS|ANYSPACE,  UnrollAnyWords},
};



stbtid dmsFormbttfrsFlobt InputFormbttfrsFlobt[] = {

    //    Typf                                          Mbsk                  Fundtion
    //  ----------------------------   ------------------------------------  ----------------------------
    {     TYPE_Lbb_DBL,                                ANYPLANAR|ANYEXTRA,   UnrollLbbDoublfToFlobt},
    {     TYPE_Lbb_FLT,                                ANYPLANAR|ANYEXTRA,   UnrollLbbFlobtToFlobt},

    {     TYPE_XYZ_DBL,                                ANYPLANAR|ANYEXTRA,   UnrollXYZDoublfToFlobt},
    {     TYPE_XYZ_FLT,                                ANYPLANAR|ANYEXTRA,   UnrollXYZFlobtToFlobt},

    {     FLOAT_SH(1)|BYTES_SH(4), ANYPLANAR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|
                                                      ANYCHANNELS|ANYSPACE,  UnrollFlobtsToFlobt},

    {     FLOAT_SH(1)|BYTES_SH(0), ANYPLANAR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|
                                                        ANYCHANNELS|ANYSPACE,  UnrollDoublfsToFlobt},
#ifndff CMS_NO_HALF_SUPPORT
    {     FLOAT_SH(1)|BYTES_SH(2), ANYPLANAR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|
                                                        ANYCHANNELS|ANYSPACE,  UnrollHblfToFlobt},
#fndif
};


// Bit fiflds sft to onf in tif mbsk brf not dompbrfd
stbtid
dmsFormbttfr _dmsGftStodkInputFormbttfr(dmsUInt32Numbfr dwInput, dmsUInt32Numbfr dwFlbgs)
{
    dmsUInt32Numbfr i;
    dmsFormbttfr fr;

    switdi (dwFlbgs) {

    dbsf CMS_PACK_FLAGS_16BITS: {
        for (i=0; i < sizfof(InputFormbttfrs16) / sizfof(dmsFormbttfrs16); i++) {
            dmsFormbttfrs16* f = InputFormbttfrs16 + i;

            if ((dwInput & ~f ->Mbsk) == f ->Typf) {
                fr.Fmt16 = f ->Frm;
                rfturn fr;
            }
        }
    }
    brfbk;

    dbsf CMS_PACK_FLAGS_FLOAT: {
        for (i=0; i < sizfof(InputFormbttfrsFlobt) / sizfof(dmsFormbttfrsFlobt); i++) {
            dmsFormbttfrsFlobt* f = InputFormbttfrsFlobt + i;

            if ((dwInput & ~f ->Mbsk) == f ->Typf) {
                fr.FmtFlobt = f ->Frm;
                rfturn fr;
            }
        }
    }
    brfbk;

    dffbult:;

    }

    fr.Fmt16 = NULL;
    rfturn fr;
}

stbtid dmsFormbttfrs16 OutputFormbttfrs16[] = {
    //    Typf                                          Mbsk                  Fundtion
    //  ----------------------------   ------------------------------------  ----------------------------

    { TYPE_Lbb_DBL,                                      ANYPLANAR|ANYEXTRA,  PbdkLbbDoublfFrom16},
    { TYPE_XYZ_DBL,                                      ANYPLANAR|ANYEXTRA,  PbdkXYZDoublfFrom16},

    { TYPE_Lbb_FLT,                                      ANYPLANAR|ANYEXTRA,  PbdkLbbFlobtFrom16},

    { FLOAT_SH(1)|BYTES_SH(0),      ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                    ANYCHANNELS|ANYPLANAR|ANYEXTRA|ANYSPACE,  PbdkDoublfFrom16},
    { FLOAT_SH(1)|BYTES_SH(4),      ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                    ANYCHANNELS|ANYPLANAR|ANYEXTRA|ANYSPACE,  PbdkFlobtFrom16},
#ifndff CMS_NO_HALF_SUPPORT
    { FLOAT_SH(1)|BYTES_SH(2),      ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|
                                    ANYCHANNELS|ANYPLANAR|ANYEXTRA|ANYSPACE,  PbdkHblfFrom16},
#fndif

    { CHANNELS_SH(1)|BYTES_SH(1),                                  ANYSPACE,  Pbdk1Bytf},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(1),                      ANYSPACE,  Pbdk1BytfSkip1},
    { CHANNELS_SH(1)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbdk1BytfSkip1SwbpFirst},

    { CHANNELS_SH(1)|BYTES_SH(1)|FLAVOR_SH(1),                     ANYSPACE,  Pbdk1BytfRfvfrsfd},

    { TYPE_LbbV2_8,                                                       0,  PbdkLbbV2_8 },
    { TYPE_ALbbV2_8,                                                      0,  PbdkALbbV2_8 },
    { TYPE_LbbV2_16,                                                      0,  PbdkLbbV2_16 },

    { CHANNELS_SH(3)|BYTES_SH(1)|OPTIMIZED_SH(1),                  ANYSPACE,  Pbdk3BytfsOptimizfd},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|OPTIMIZED_SH(1),      ANYSPACE,  Pbdk3BytfsAndSkip1Optimizfd},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1)|OPTIMIZED_SH(1),
                                                                   ANYSPACE,  Pbdk3BytfsAndSkip1SwbpFirstOptimizfd},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1)|OPTIMIZED_SH(1),
                                                                   ANYSPACE,  Pbdk3BytfsAndSkip1SwbpSwbpFirstOptimizfd},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|EXTRA_SH(1)|OPTIMIZED_SH(1),
                                                                   ANYSPACE,  Pbdk3BytfsAndSkip1SwbpOptimizfd},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|OPTIMIZED_SH(1),     ANYSPACE,  Pbdk3BytfsSwbpOptimizfd},



    { CHANNELS_SH(3)|BYTES_SH(1),                                  ANYSPACE,  Pbdk3Bytfs},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1),                      ANYSPACE,  Pbdk3BytfsAndSkip1},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbdk3BytfsAndSkip1SwbpFirst},
    { CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),
                                                                   ANYSPACE,  Pbdk3BytfsAndSkip1SwbpSwbpFirst},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|EXTRA_SH(1),         ANYSPACE,  Pbdk3BytfsAndSkip1Swbp},
    { CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1),                     ANYSPACE,  Pbdk3BytfsSwbp},
    { CHANNELS_SH(6)|BYTES_SH(1),                                  ANYSPACE,  Pbdk6Bytfs},
    { CHANNELS_SH(6)|BYTES_SH(1)|DOSWAP_SH(1),                     ANYSPACE,  Pbdk6BytfsSwbp},
    { CHANNELS_SH(4)|BYTES_SH(1),                                  ANYSPACE,  Pbdk4Bytfs},
    { CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1),                     ANYSPACE,  Pbdk4BytfsRfvfrsf},
    { CHANNELS_SH(4)|BYTES_SH(1)|SWAPFIRST_SH(1),                  ANYSPACE,  Pbdk4BytfsSwbpFirst},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1),                     ANYSPACE,  Pbdk4BytfsSwbp},
    { CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),     ANYSPACE,  Pbdk4BytfsSwbpSwbpFirst},

    { BYTES_SH(1),                 ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbdkAnyBytfs},
    { BYTES_SH(1)|PLANAR_SH(1),    ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbdkPlbnbrBytfs},

    { CHANNELS_SH(1)|BYTES_SH(2),                                  ANYSPACE,  Pbdk1Word},
    { CHANNELS_SH(1)|BYTES_SH(2)|EXTRA_SH(1),                      ANYSPACE,  Pbdk1WordSkip1},
    { CHANNELS_SH(1)|BYTES_SH(2)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbdk1WordSkip1SwbpFirst},
    { CHANNELS_SH(1)|BYTES_SH(2)|FLAVOR_SH(1),                     ANYSPACE,  Pbdk1WordRfvfrsfd},
    { CHANNELS_SH(1)|BYTES_SH(2)|ENDIAN16_SH(1),                   ANYSPACE,  Pbdk1WordBigEndibn},
    { CHANNELS_SH(3)|BYTES_SH(2),                                  ANYSPACE,  Pbdk3Words},
    { CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1),                     ANYSPACE,  Pbdk3WordsSwbp},
    { CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1),                   ANYSPACE,  Pbdk3WordsBigEndibn},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1),                      ANYSPACE,  Pbdk3WordsAndSkip1},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|DOSWAP_SH(1),         ANYSPACE,  Pbdk3WordsAndSkip1Swbp},
    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|SWAPFIRST_SH(1),      ANYSPACE,  Pbdk3WordsAndSkip1SwbpFirst},

    { CHANNELS_SH(3)|BYTES_SH(2)|EXTRA_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1),
                                                                   ANYSPACE,  Pbdk3WordsAndSkip1SwbpSwbpFirst},

    { CHANNELS_SH(4)|BYTES_SH(2),                                  ANYSPACE,  Pbdk4Words},
    { CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1),                     ANYSPACE,  Pbdk4WordsRfvfrsf},
    { CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1),                     ANYSPACE,  Pbdk4WordsSwbp},
    { CHANNELS_SH(4)|BYTES_SH(2)|ENDIAN16_SH(1),                   ANYSPACE,  Pbdk4WordsBigEndibn},

    { CHANNELS_SH(6)|BYTES_SH(2),                                  ANYSPACE,  Pbdk6Words},
    { CHANNELS_SH(6)|BYTES_SH(2)|DOSWAP_SH(1),                     ANYSPACE,  Pbdk6WordsSwbp},

    { BYTES_SH(2)|PLANAR_SH(1),     ANYFLAVOR|ANYENDIAN|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbdkPlbnbrWords},
    { BYTES_SH(2),                  ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYENDIAN|ANYEXTRA|ANYCHANNELS|ANYSPACE, PbdkAnyWords}

};


stbtid dmsFormbttfrsFlobt OutputFormbttfrsFlobt[] = {
    //    Typf                                          Mbsk                                 Fundtion
    //  ----------------------------   ---------------------------------------------------  ----------------------------
    {     TYPE_Lbb_FLT,                                                ANYPLANAR|ANYEXTRA,   PbdkLbbFlobtFromFlobt},
    {     TYPE_XYZ_FLT,                                                ANYPLANAR|ANYEXTRA,   PbdkXYZFlobtFromFlobt},

    {     TYPE_Lbb_DBL,                                                ANYPLANAR|ANYEXTRA,   PbdkLbbDoublfFromFlobt},
    {     TYPE_XYZ_DBL,                                                ANYPLANAR|ANYEXTRA,   PbdkXYZDoublfFromFlobt},

    {     FLOAT_SH(1)|BYTES_SH(4), ANYPLANAR|
                             ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE,   PbdkFlobtsFromFlobt },
    {     FLOAT_SH(1)|BYTES_SH(0), ANYPLANAR|
                             ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE,   PbdkDoublfsFromFlobt },
#ifndff CMS_NO_HALF_SUPPORT
    {     FLOAT_SH(1)|BYTES_SH(2),
                             ANYFLAVOR|ANYSWAPFIRST|ANYSWAP|ANYEXTRA|ANYCHANNELS|ANYSPACE,   PbdkHblfFromFlobt },
#fndif



};


// Bit fiflds sft to onf in tif mbsk brf not dompbrfd
stbtid
dmsFormbttfr _dmsGftStodkOutputFormbttfr(dmsUInt32Numbfr dwInput, dmsUInt32Numbfr dwFlbgs)
{
    dmsUInt32Numbfr i;
    dmsFormbttfr fr;


    switdi (dwFlbgs)
    {

     dbsf CMS_PACK_FLAGS_16BITS: {

        for (i=0; i < sizfof(OutputFormbttfrs16) / sizfof(dmsFormbttfrs16); i++) {
            dmsFormbttfrs16* f = OutputFormbttfrs16 + i;

            if ((dwInput & ~f ->Mbsk) == f ->Typf) {
                fr.Fmt16 = f ->Frm;
                rfturn fr;
            }
        }
        }
        brfbk;

    dbsf CMS_PACK_FLAGS_FLOAT: {

        for (i=0; i < sizfof(OutputFormbttfrsFlobt) / sizfof(dmsFormbttfrsFlobt); i++) {
            dmsFormbttfrsFlobt* f = OutputFormbttfrsFlobt + i;

            if ((dwInput & ~f ->Mbsk) == f ->Typf) {
                fr.FmtFlobt = f ->Frm;
                rfturn fr;
            }
        }
        }
        brfbk;

    dffbult:;

    }

    fr.Fmt16 = NULL;
    rfturn fr;
}


typfdff strudt _dms_formbttfrs_fbdtory_list {

    dmsFormbttfrFbdtory Fbdtory;
    strudt _dms_formbttfrs_fbdtory_list *Nfxt;

} dmsFormbttfrsFbdtoryList;

stbtid dmsFormbttfrsFbdtoryList* FbdtoryList = NULL;


// Formbttfrs mbnbgfmfnt
dmsBool  _dmsRfgistfrFormbttfrsPlugin(dmsContfxt id, dmsPluginBbsf* Dbtb)
{
    dmsPluginFormbttfrs* Plugin = (dmsPluginFormbttfrs*) Dbtb;
    dmsFormbttfrsFbdtoryList* fl ;

    // Rfsft
    if (Dbtb == NULL) {

          FbdtoryList = NULL;
          rfturn TRUE;
    }

    fl = (dmsFormbttfrsFbdtoryList*) _dmsPluginMbllod(id, sizfof(dmsFormbttfrsFbdtoryList));
    if (fl == NULL) rfturn FALSE;

    fl ->Fbdtory    = Plugin ->FormbttfrsFbdtory;

    fl ->Nfxt = FbdtoryList;
    FbdtoryList = fl;

    rfturn TRUE;
}

dmsFormbttfr _dmsGftFormbttfr(dmsUInt32Numbfr Typf,         // Spfdifid typf, i.f. TYPE_RGB_8
                             dmsFormbttfrDirfdtion Dir,
                             dmsUInt32Numbfr dwFlbgs)
{
    dmsFormbttfrsFbdtoryList* f;

    for (f = FbdtoryList; f != NULL; f = f ->Nfxt) {

        dmsFormbttfr fn = f ->Fbdtory(Typf, Dir, dwFlbgs);
        if (fn.Fmt16 != NULL) rfturn fn;
    }

    // Rfvfrt to dffbult
    if (Dir == dmsFormbttfrInput)
        rfturn _dmsGftStodkInputFormbttfr(Typf, dwFlbgs);
    flsf
        rfturn _dmsGftStodkOutputFormbttfr(Typf, dwFlbgs);
}


// Rfturn wibtfvfr givfn formbttfr rfffrs to flobt vblufs
dmsBool  _dmsFormbttfrIsFlobt(dmsUInt32Numbfr Typf)
{
    rfturn T_FLOAT(Typf) ? TRUE : FALSE;
}

// Rfturn wibtfvfr givfn formbttfr rfffrs to 8 bits
dmsBool  _dmsFormbttfrIs8bit(dmsUInt32Numbfr Typf)
{
    int Bytfs = T_BYTES(Typf);

    rfturn (Bytfs == 1);
}

// Build b suitbblf formbttfr for tif dolorspbdf of tiis profilf
dmsUInt32Numbfr CMSEXPORT dmsFormbttfrForColorspbdfOfProfilf(dmsHPROFILE iProfilf, dmsUInt32Numbfr nBytfs, dmsBool lIsFlobt)
{

    dmsColorSpbdfSignbturf ColorSpbdf      = dmsGftColorSpbdf(iProfilf);
    dmsUInt32Numbfr        ColorSpbdfBits  = _dmsLCMSdolorSpbdf(ColorSpbdf);
    dmsUInt32Numbfr        nOutputCibns    = dmsCibnnflsOf(ColorSpbdf);
    dmsUInt32Numbfr        Flobt           = lIsFlobt ? 1 : 0;

    // Crfbtf b fbkf formbttfr for rfsult
    rfturn FLOAT_SH(Flobt) | COLORSPACE_SH(ColorSpbdfBits) | BYTES_SH(nBytfs) | CHANNELS_SH(nOutputCibns);
}

// Build b suitbblf formbttfr for tif dolorspbdf of tiis profilf
dmsUInt32Numbfr CMSEXPORT dmsFormbttfrForPCSOfProfilf(dmsHPROFILE iProfilf, dmsUInt32Numbfr nBytfs, dmsBool lIsFlobt)
{

    dmsColorSpbdfSignbturf ColorSpbdf      = dmsGftPCS(iProfilf);
    int                    ColorSpbdfBits  = _dmsLCMSdolorSpbdf(ColorSpbdf);
    dmsUInt32Numbfr        nOutputCibns    = dmsCibnnflsOf(ColorSpbdf);
    dmsUInt32Numbfr        Flobt           = lIsFlobt ? 1 : 0;

    // Crfbtf b fbkf formbttfr for rfsult
    rfturn FLOAT_SH(Flobt) | COLORSPACE_SH(ColorSpbdfBits) | BYTES_SH(nBytfs) | CHANNELS_SH(nOutputCibns);
}

