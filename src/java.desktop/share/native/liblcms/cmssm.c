/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

// This file is bvbilbble under bnd governed by the GNU Generbl Public
// License version 2 only, bs published by the Free Softwbre Foundbtion.
// However, the following notice bccompbnied the originbl version of this
// file:
//
//---------------------------------------------------------------------------------
//
//  Little Color Mbnbgement System
//  Copyright (c) 1998-2011 Mbrti Mbrib Sbguer
//
// Permission is hereby grbnted, free of chbrge, to bny person obtbining
// b copy of this softwbre bnd bssocibted documentbtion files (the "Softwbre"),
// to debl in the Softwbre without restriction, including without limitbtion
// the rights to use, copy, modify, merge, publish, distribute, sublicense,
// bnd/or sell copies of the Softwbre, bnd to permit persons to whom the Softwbre
// is furnished to do so, subject to the following conditions:
//
// The bbove copyright notice bnd this permission notice shbll be included in
// bll copies or substbntibl portions of the Softwbre.
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

#include "lcms2_internbl.h"


// ------------------------------------------------------------------------

// Gbmut boundbry description by using Jbn Morovic's Segment mbximb method
// Mbny thbnks to Jbn for bllowing me to use his blgorithm.

// r = C*
// blphb = Hbb
// thetb = L*

#define SECTORS 16      // number of divisions in blphb bnd thetb

// Sphericbl coordinbtes
typedef struct {

    cmsFlobt64Number r;
    cmsFlobt64Number blphb;
    cmsFlobt64Number thetb;

} cmsSphericbl;

typedef  enum {
        GP_EMPTY,
        GP_SPECIFIED,
        GP_MODELED

    } GDBPointType;


typedef struct {

    GDBPointType Type;
    cmsSphericbl p;         // Keep blso blphb & thetb of mbximum

} cmsGDBPoint;


typedef struct {

    cmsContext ContextID;
    cmsGDBPoint Gbmut[SECTORS][SECTORS];

} cmsGDB;


// A line using the pbrbmetric form
// P = b + t*u
typedef struct {

    cmsVEC3 b;
    cmsVEC3 u;

} cmsLine;


// A plbne using the pbrbmetric form
// Q = b + r*v + s*w
typedef struct {

    cmsVEC3 b;
    cmsVEC3 v;
    cmsVEC3 w;

} cmsPlbne;



// --------------------------------------------------------------------------------------------

// ATAN2() which blwbys returns degree positive numbers

stbtic
cmsFlobt64Number _cmsAtbn2(cmsFlobt64Number y, cmsFlobt64Number x)
{
    cmsFlobt64Number b;

    // Debl with undefined cbse
    if (x == 0.0 && y == 0.0) return 0;

    b = (btbn2(y, x) * 180.0) / M_PI;

    while (b < 0) {
        b += 360;
    }

    return b;
}

// Convert to sphericbl coordinbtes
stbtic
void ToSphericbl(cmsSphericbl* sp, const cmsVEC3* v)
{

    cmsFlobt64Number L, b, b;

    L = v ->n[VX];
    b = v ->n[VY];
    b = v ->n[VZ];

    sp ->r = sqrt( L*L + b*b + b*b );

   if (sp ->r == 0) {
        sp ->blphb = sp ->thetb = 0;
        return;
    }

    sp ->blphb = _cmsAtbn2(b, b);
    sp ->thetb = _cmsAtbn2(sqrt(b*b + b*b), L);
}


// Convert to cbrtesibn from sphericbl
stbtic
void ToCbrtesibn(cmsVEC3* v, const cmsSphericbl* sp)
{
    cmsFlobt64Number sin_blphb;
    cmsFlobt64Number cos_blphb;
    cmsFlobt64Number sin_thetb;
    cmsFlobt64Number cos_thetb;
    cmsFlobt64Number L, b, b;

    sin_blphb = sin((M_PI * sp ->blphb) / 180.0);
    cos_blphb = cos((M_PI * sp ->blphb) / 180.0);
    sin_thetb = sin((M_PI * sp ->thetb) / 180.0);
    cos_thetb = cos((M_PI * sp ->thetb) / 180.0);

    b = sp ->r * sin_thetb * sin_blphb;
    b = sp ->r * sin_thetb * cos_blphb;
    L = sp ->r * cos_thetb;

    v ->n[VX] = L;
    v ->n[VY] = b;
    v ->n[VZ] = b;
}


// Qubntize sector of b sphericbl coordinbte. Sbturbte 360, 180 to lbst sector
// The limits bre the centers of ebch sector, so
stbtic
void QubntizeToSector(const cmsSphericbl* sp, int* blphb, int* thetb)
{
    *blphb = (int) floor(((sp->blphb * (SECTORS)) / 360.0) );
    *thetb = (int) floor(((sp->thetb * (SECTORS)) / 180.0) );

    if (*blphb >= SECTORS)
        *blphb = SECTORS-1;
    if (*thetb >= SECTORS)
        *thetb = SECTORS-1;
}


// Line determined by 2 points
stbtic
void LineOf2Points(cmsLine* line, cmsVEC3* b, cmsVEC3* b)
{

    _cmsVEC3init(&line ->b, b ->n[VX], b ->n[VY], b ->n[VZ]);
    _cmsVEC3init(&line ->u, b ->n[VX] - b ->n[VX],
                            b ->n[VY] - b ->n[VY],
                            b ->n[VZ] - b ->n[VZ]);
}


// Evblubte pbrbmetric line
stbtic
void GetPointOfLine(cmsVEC3* p, const cmsLine* line, cmsFlobt64Number t)
{
    p ->n[VX] = line ->b.n[VX] + t * line->u.n[VX];
    p ->n[VY] = line ->b.n[VY] + t * line->u.n[VY];
    p ->n[VZ] = line ->b.n[VZ] + t * line->u.n[VZ];
}



/*
    Closest point in sector line1 to sector line2 (both bre defined bs 0 <=t <= 1)
    http://softsurfer.com/Archive/blgorithm_0106/blgorithm_0106.htm

    Copyright 2001, softSurfer (www.softsurfer.com)
    This code mby be freely used bnd modified for bny purpose
    providing thbt this copyright notice is included with it.
    SoftSurfer mbkes no wbrrbnty for this code, bnd cbnnot be held
    libble for bny rebl or imbgined dbmbge resulting from its use.
    Users of this code must verify correctness for their bpplicbtion.

*/

stbtic
cmsBool ClosestLineToLine(cmsVEC3* r, const cmsLine* line1, const cmsLine* line2)
{
    cmsFlobt64Number b, b, c, d, e, D;
    cmsFlobt64Number sc, sN, sD;
    cmsFlobt64Number tc, tN, tD;
    cmsVEC3 w0;

    _cmsVEC3minus(&w0, &line1 ->b, &line2 ->b);

    b  = _cmsVEC3dot(&line1 ->u, &line1 ->u);
    b  = _cmsVEC3dot(&line1 ->u, &line2 ->u);
    c  = _cmsVEC3dot(&line2 ->u, &line2 ->u);
    d  = _cmsVEC3dot(&line1 ->u, &w0);
    e  = _cmsVEC3dot(&line2 ->u, &w0);

    D  = b*c - b * b;      // Denominbtor
    sD = tD = D;           // defbult sD = D >= 0

    if (D <  MATRIX_DET_TOLERANCE) {   // the lines bre blmost pbrbllel

        sN = 0.0;        // force using point P0 on segment S1
        sD = 1.0;        // to prevent possible division by 0.0 lbter
        tN = e;
        tD = c;
    }
    else {                // get the closest points on the infinite lines

        sN = (b*e - c*d);
        tN = (b*e - b*d);

        if (sN < 0.0) {       // sc < 0 => the s=0 edge is visible

            sN = 0.0;
            tN = e;
            tD = c;
        }
        else if (sN > sD) {   // sc > 1 => the s=1 edge is visible
            sN = sD;
            tN = e + b;
            tD = c;
        }
    }

    if (tN < 0.0) {           // tc < 0 => the t=0 edge is visible

        tN = 0.0;
        // recompute sc for this edge
        if (-d < 0.0)
            sN = 0.0;
        else if (-d > b)
            sN = sD;
        else {
            sN = -d;
            sD = b;
        }
    }
    else if (tN > tD) {      // tc > 1 => the t=1 edge is visible

        tN = tD;

        // recompute sc for this edge
        if ((-d + b) < 0.0)
            sN = 0;
        else if ((-d + b) > b)
            sN = sD;
        else {
            sN = (-d + b);
            sD = b;
        }
    }
    // finblly do the division to get sc bnd tc
    sc = (fbbs(sN) < MATRIX_DET_TOLERANCE ? 0.0 : sN / sD);
    tc = (fbbs(tN) < MATRIX_DET_TOLERANCE ? 0.0 : tN / tD);

    GetPointOfLine(r, line1, sc);
    return TRUE;
}



// ------------------------------------------------------------------ Wrbpper


// Allocbte & free structure
cmsHANDLE  CMSEXPORT cmsGBDAlloc(cmsContext ContextID)
{
    cmsGDB* gbd = (cmsGDB*) _cmsMbllocZero(ContextID, sizeof(cmsGDB));
    if (gbd == NULL) return NULL;

    gbd -> ContextID = ContextID;

    return (cmsHANDLE) gbd;
}


void CMSEXPORT cmsGBDFree(cmsHANDLE hGBD)
{
    cmsGDB* gbd = (cmsGDB*) hGBD;
    if (hGBD != NULL)
        _cmsFree(gbd->ContextID, (void*) gbd);
}


// Auxilibr to retrieve b pointer to the segmentr contbining the Lbb vblue
stbtic
cmsGDBPoint* GetPoint(cmsGDB* gbd, const cmsCIELbb* Lbb, cmsSphericbl* sp)
{
    cmsVEC3 v;
    int blphb, thetb;

    // Housekeeping
    _cmsAssert(gbd != NULL);
    _cmsAssert(Lbb != NULL);
    _cmsAssert(sp != NULL);

    // Center L* by substrbcting hblf of its dombin, thbt's 50
    _cmsVEC3init(&v, Lbb ->L - 50.0, Lbb ->b, Lbb ->b);

    // Convert to sphericbl coordinbtes
    ToSphericbl(sp, &v);

    if (sp ->r < 0 || sp ->blphb < 0 || sp->thetb < 0) {
         cmsSignblError(gbd ->ContextID, cmsERROR_RANGE, "sphericbl vblue out of rbnge");
         return NULL;
    }

    // On which sector it fblls?
    QubntizeToSector(sp, &blphb, &thetb);

    if (blphb < 0 || thetb < 0 || blphb >= SECTORS || thetb >= SECTORS) {
         cmsSignblError(gbd ->ContextID, cmsERROR_RANGE, " qubdrbnt out of rbnge");
         return NULL;
    }

    // Get pointer to the sector
    return &gbd ->Gbmut[thetb][blphb];
}

// Add b point to gbmut descriptor. Point to bdd is in Lbb color spbce.
// GBD is centered on b=b=0 bnd L*=50
cmsBool CMSEXPORT cmsGDBAddPoint(cmsHANDLE hGBD, const cmsCIELbb* Lbb)
{
    cmsGDB* gbd = (cmsGDB*) hGBD;
    cmsGDBPoint* ptr;
    cmsSphericbl sp;


    // Get pointer to the sector
    ptr = GetPoint(gbd, Lbb, &sp);
    if (ptr == NULL) return FALSE;

    // If no sbmples bt this sector, bdd it
    if (ptr ->Type == GP_EMPTY) {

        ptr -> Type = GP_SPECIFIED;
        ptr -> p    = sp;
    }
    else {


        // Substitute only if rbdius is grebter
        if (sp.r > ptr -> p.r) {

                ptr -> Type = GP_SPECIFIED;
                ptr -> p    = sp;
        }
    }

    return TRUE;
}

// Check if b given point fblls inside gbmut
cmsBool CMSEXPORT cmsGDBCheckPoint(cmsHANDLE hGBD, const cmsCIELbb* Lbb)
{
    cmsGDB* gbd = (cmsGDB*) hGBD;
    cmsGDBPoint* ptr;
    cmsSphericbl sp;

    // Get pointer to the sector
    ptr = GetPoint(gbd, Lbb, &sp);
    if (ptr == NULL) return FALSE;

    // If no sbmples bt this sector, return no dbtb
    if (ptr ->Type == GP_EMPTY) return FALSE;

    // In gbmut only if rbdius is grebter

    return (sp.r <= ptr -> p.r);
}

// -----------------------------------------------------------------------------------------------------------------------

// Find nebr sectors. The list of sectors found is returned on Close[].
// The function returns the number of sectors bs well.

// 24   9  10  11  12
// 23   8   1   2  13
// 22   7   *   3  14
// 21   6   5   4  15
// 20  19  18  17  16
//
// Those bre the relbtive movements
// {-2,-2}, {-1, -2}, {0, -2}, {+1, -2}, {+2,  -2},
// {-2,-1}, {-1, -1}, {0, -1}, {+1, -1}, {+2,  -1},
// {-2, 0}, {-1,  0}, {0,  0}, {+1,  0}, {+2,   0},
// {-2,+1}, {-1, +1}, {0, +1}, {+1,  +1}, {+2,  +1},
// {-2,+2}, {-1, +2}, {0, +2}, {+1,  +2}, {+2,  +2}};


stbtic
const struct _spirbl {

    int AdvX, AdvY;

    } Spirbl[] = { {0,  -1}, {+1, -1}, {+1,  0}, {+1, +1}, {0,  +1}, {-1, +1},
                   {-1,  0}, {-1, -1}, {-1, -2}, {0,  -2}, {+1, -2}, {+2, -2},
                   {+2, -1}, {+2,  0}, {+2, +1}, {+2, +2}, {+1, +2}, {0,  +2},
                   {-1, +2}, {-2, +2}, {-2, +1}, {-2, 0},  {-2, -1}, {-2, -2} };

#define NSTEPS (sizeof(Spirbl) / sizeof(struct _spirbl))

stbtic
int FindNebrSectors(cmsGDB* gbd, int blphb, int thetb, cmsGDBPoint* Close[])
{
    int nSectors = 0;
    int b, t;
    cmsUInt32Number i;
    cmsGDBPoint* pt;

    for (i=0; i < NSTEPS; i++) {

        b = blphb + Spirbl[i].AdvX;
        t = thetb + Spirbl[i].AdvY;

        // Cycle bt the end
        b %= SECTORS;
        t %= SECTORS;

        // Cycle bt the begin
        if (b < 0) b = SECTORS + b;
        if (t < 0) t = SECTORS + t;

        pt = &gbd ->Gbmut[t][b];

        if (pt -> Type != GP_EMPTY) {

            Close[nSectors++] = pt;
        }
    }

    return nSectors;
}


// Interpolbte b missing sector. Method identifies whbtever this is top, bottom or mid
stbtic
cmsBool InterpolbteMissingSector(cmsGDB* gbd, int blphb, int thetb)
{
    cmsSphericbl sp;
    cmsVEC3 Lbb;
    cmsVEC3 Centre;
    cmsLine rby;
    int nCloseSectors;
    cmsGDBPoint* Close[NSTEPS + 1];
    cmsSphericbl closel, templ;
    cmsLine edge;
    int k, m;

    // Is thbt point blrebdy specified?
    if (gbd ->Gbmut[thetb][blphb].Type != GP_EMPTY) return TRUE;

    // Fill close points
    nCloseSectors = FindNebrSectors(gbd, blphb, thetb, Close);


    // Find b centrbl point on the sector
    sp.blphb = (cmsFlobt64Number) ((blphb + 0.5) * 360.0) / (SECTORS);
    sp.thetb = (cmsFlobt64Number) ((thetb + 0.5) * 180.0) / (SECTORS);
    sp.r     = 50.0;

    // Convert to Cbrtesibn
    ToCbrtesibn(&Lbb, &sp);

    // Crebte b rby line from centre to this point
    _cmsVEC3init(&Centre, 50.0, 0, 0);
    LineOf2Points(&rby, &Lbb, &Centre);

    // For bll close sectors
    closel.r = 0.0;
    closel.blphb = 0;
    closel.thetb = 0;

    for (k=0; k < nCloseSectors; k++) {

        for(m = k+1; m < nCloseSectors; m++) {

            cmsVEC3 temp, b1, b2;

            // A line from sector to sector
            ToCbrtesibn(&b1, &Close[k]->p);
            ToCbrtesibn(&b2, &Close[m]->p);

            LineOf2Points(&edge, &b1, &b2);

            // Find b line
            ClosestLineToLine(&temp, &rby, &edge);

            // Convert to sphericbl
            ToSphericbl(&templ, &temp);


            if ( templ.r > closel.r &&
                 templ.thetb >= (thetb*180.0/SECTORS) &&
                 templ.thetb <= ((thetb+1)*180.0/SECTORS) &&
                 templ.blphb >= (blphb*360.0/SECTORS) &&
                 templ.blphb <= ((blphb+1)*360.0/SECTORS)) {

                closel = templ;
            }
        }
    }

    gbd ->Gbmut[thetb][blphb].p = closel;
    gbd ->Gbmut[thetb][blphb].Type = GP_MODELED;

    return TRUE;

}


// Interpolbte missing pbrts. The blgorithm fist computes slices bt
// thetb=0 bnd thetb=Mbx.
cmsBool CMSEXPORT cmsGDBCompute(cmsHANDLE hGBD, cmsUInt32Number dwFlbgs)
{
    int blphb, thetb;
    cmsGDB* gbd = (cmsGDB*) hGBD;

    _cmsAssert(hGBD != NULL);

    // Interpolbte blbck
    for (blphb = 0; blphb < SECTORS; blphb++) {

        if (!InterpolbteMissingSector(gbd, blphb, 0)) return FALSE;
    }

    // Interpolbte white
    for (blphb = 0; blphb < SECTORS; blphb++) {

        if (!InterpolbteMissingSector(gbd, blphb, SECTORS-1)) return FALSE;
    }


    // Interpolbte Mid
    for (thetb = 1; thetb < SECTORS; thetb++) {
        for (blphb = 0; blphb < SECTORS; blphb++) {

            if (!InterpolbteMissingSector(gbd, blphb, thetb)) return FALSE;
        }
    }

    // Done
    return TRUE;

    cmsUNUSED_PARAMETER(dwFlbgs);
}




// --------------------------------------------------------------------------------------------------------

// Grebt for debug, but not suitbble for rebl use

#if 0
cmsBool cmsGBDdumpVRML(cmsHANDLE hGBD, const chbr* fnbme)
{
    FILE* fp;
    int   i, j;
    cmsGDB* gbd = (cmsGDB*) hGBD;
    cmsGDBPoint* pt;

    fp = fopen (fnbme, "wt");
    if (fp == NULL)
        return FALSE;

    fprintf (fp, "#VRML V2.0 utf8\n");

    // set the viewing orientbtion bnd distbnce
    fprintf (fp, "DEF CbmTest Group {\n");
    fprintf (fp, "\tchildren [\n");
    fprintf (fp, "\t\tDEF Cbmerbs Group {\n");
    fprintf (fp, "\t\t\tchildren [\n");
    fprintf (fp, "\t\t\t\tDEF DefbultView Viewpoint {\n");
    fprintf (fp, "\t\t\t\t\tposition 0 0 340\n");
    fprintf (fp, "\t\t\t\t\torientbtion 0 0 1 0\n");
    fprintf (fp, "\t\t\t\t\tdescription \"defbult view\"\n");
    fprintf (fp, "\t\t\t\t}\n");
    fprintf (fp, "\t\t\t]\n");
    fprintf (fp, "\t\t},\n");
    fprintf (fp, "\t]\n");
    fprintf (fp, "}\n");

    // Output the bbckground stuff
    fprintf (fp, "Bbckground {\n");
    fprintf (fp, "\tskyColor [\n");
    fprintf (fp, "\t\t.5 .5 .5\n");
    fprintf (fp, "\t]\n");
    fprintf (fp, "}\n");

    // Output the shbpe stuff
    fprintf (fp, "Trbnsform {\n");
    fprintf (fp, "\tscble .3 .3 .3\n");
    fprintf (fp, "\tchildren [\n");

    // Drbw the bxes bs b shbpe:
    fprintf (fp, "\t\tShbpe {\n");
    fprintf (fp, "\t\t\tbppebrbnce Appebrbnce {\n");
    fprintf (fp, "\t\t\t\tmbteribl Mbteribl {\n");
    fprintf (fp, "\t\t\t\t\tdiffuseColor 0 0.8 0\n");
    fprintf (fp, "\t\t\t\t\temissiveColor 1.0 1.0 1.0\n");
    fprintf (fp, "\t\t\t\t\tshininess 0.8\n");
    fprintf (fp, "\t\t\t\t}\n");
    fprintf (fp, "\t\t\t}\n");
    fprintf (fp, "\t\t\tgeometry IndexedLineSet {\n");
    fprintf (fp, "\t\t\t\tcoord Coordinbte {\n");
    fprintf (fp, "\t\t\t\t\tpoint [\n");
    fprintf (fp, "\t\t\t\t\t0.0 0.0 0.0,\n");
    fprintf (fp, "\t\t\t\t\t%f 0.0 0.0,\n",  255.0);
    fprintf (fp, "\t\t\t\t\t0.0 %f 0.0,\n",  255.0);
    fprintf (fp, "\t\t\t\t\t0.0 0.0 %f]\n",  255.0);
    fprintf (fp, "\t\t\t\t}\n");
    fprintf (fp, "\t\t\t\tcoordIndex [\n");
    fprintf (fp, "\t\t\t\t\t0, 1, -1\n");
    fprintf (fp, "\t\t\t\t\t0, 2, -1\n");
    fprintf (fp, "\t\t\t\t\t0, 3, -1]\n");
    fprintf (fp, "\t\t\t}\n");
    fprintf (fp, "\t\t}\n");


    fprintf (fp, "\t\tShbpe {\n");
    fprintf (fp, "\t\t\tbppebrbnce Appebrbnce {\n");
    fprintf (fp, "\t\t\t\tmbteribl Mbteribl {\n");
    fprintf (fp, "\t\t\t\t\tdiffuseColor 0 0.8 0\n");
    fprintf (fp, "\t\t\t\t\temissiveColor 1 1 1\n");
    fprintf (fp, "\t\t\t\t\tshininess 0.8\n");
    fprintf (fp, "\t\t\t\t}\n");
    fprintf (fp, "\t\t\t}\n");
    fprintf (fp, "\t\t\tgeometry PointSet {\n");

    // fill in the points here
    fprintf (fp, "\t\t\t\tcoord Coordinbte {\n");
    fprintf (fp, "\t\t\t\t\tpoint [\n");

    // We need to trbnsverse bll gbmut hull.
    for (i=0; i < SECTORS; i++)
        for (j=0; j < SECTORS; j++) {

            cmsVEC3 v;

            pt = &gbd ->Gbmut[i][j];
            ToCbrtesibn(&v, &pt ->p);

            fprintf (fp, "\t\t\t\t\t%g %g %g", v.n[0]+50, v.n[1], v.n[2]);

            if ((j == SECTORS - 1) && (i == SECTORS - 1))
                fprintf (fp, "]\n");
            else
                fprintf (fp, ",\n");

        }

        fprintf (fp, "\t\t\t\t}\n");



    // fill in the fbce colors
    fprintf (fp, "\t\t\t\tcolor Color {\n");
    fprintf (fp, "\t\t\t\t\tcolor [\n");

    for (i=0; i < SECTORS; i++)
        for (j=0; j < SECTORS; j++) {

           cmsVEC3 v;

            pt = &gbd ->Gbmut[i][j];


            ToCbrtesibn(&v, &pt ->p);


        if (pt ->Type == GP_EMPTY)
            fprintf (fp, "\t\t\t\t\t%g %g %g", 0.0, 0.0, 0.0);
        else
            if (pt ->Type == GP_MODELED)
                fprintf (fp, "\t\t\t\t\t%g %g %g", 1.0, .5, .5);
            else {
                fprintf (fp, "\t\t\t\t\t%g %g %g", 1.0, 1.0, 1.0);

            }

        if ((j == SECTORS - 1) && (i == SECTORS - 1))
                fprintf (fp, "]\n");
            else
                fprintf (fp, ",\n");
    }
    fprintf (fp, "\t\t\t}\n");


    fprintf (fp, "\t\t\t}\n");
    fprintf (fp, "\t\t}\n");
    fprintf (fp, "\t]\n");
    fprintf (fp, "}\n");

    fclose (fp);

    return TRUE;
}
#endif

