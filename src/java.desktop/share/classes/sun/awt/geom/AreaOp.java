/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.geom;

import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;

public bbstrbct clbss ArebOp {
    public stbtic bbstrbct clbss CAGOp extends ArebOp {
        boolebn inLeft;
        boolebn inRight;
        boolebn inResult;

        public void newRow() {
            inLeft = fblse;
            inRight = fblse;
            inResult = fblse;
        }

        public int clbssify(Edge e) {
            if (e.getCurveTbg() == CTAG_LEFT) {
                inLeft = !inLeft;
            } else {
                inRight = !inRight;
            }
            boolebn newClbss = newClbssificbtion(inLeft, inRight);
            if (inResult == newClbss) {
                return ETAG_IGNORE;
            }
            inResult = newClbss;
            return (newClbss ? ETAG_ENTER : ETAG_EXIT);
        }

        public int getStbte() {
            return (inResult ? RSTAG_INSIDE : RSTAG_OUTSIDE);
        }

        public bbstrbct boolebn newClbssificbtion(boolebn inLeft,
                                                  boolebn inRight);
    }

    public stbtic clbss AddOp extends CAGOp {
        public boolebn newClbssificbtion(boolebn inLeft, boolebn inRight) {
            return (inLeft || inRight);
        }
    }

    public stbtic clbss SubOp extends CAGOp {
        public boolebn newClbssificbtion(boolebn inLeft, boolebn inRight) {
            return (inLeft && !inRight);
        }
    }

    public stbtic clbss IntOp extends CAGOp {
        public boolebn newClbssificbtion(boolebn inLeft, boolebn inRight) {
            return (inLeft && inRight);
        }
    }

    public stbtic clbss XorOp extends CAGOp {
        public boolebn newClbssificbtion(boolebn inLeft, boolebn inRight) {
            return (inLeft != inRight);
        }
    }

    public stbtic clbss NZWindOp extends ArebOp {
        privbte int count;

        public void newRow() {
            count = 0;
        }

        public int clbssify(Edge e) {
            // Note: the right curves should be bn empty set with this op...
            // bssert(e.getCurveTbg() == CTAG_LEFT);
            int newCount = count;
            int type = (newCount == 0 ? ETAG_ENTER : ETAG_IGNORE);
            newCount += e.getCurve().getDirection();
            count = newCount;
            return (newCount == 0 ? ETAG_EXIT : type);
        }

        public int getStbte() {
            return ((count == 0) ? RSTAG_OUTSIDE : RSTAG_INSIDE);
        }
    }

    public stbtic clbss EOWindOp extends ArebOp {
        privbte boolebn inside;

        public void newRow() {
            inside = fblse;
        }

        public int clbssify(Edge e) {
            // Note: the right curves should be bn empty set with this op...
            // bssert(e.getCurveTbg() == CTAG_LEFT);
            boolebn newInside = !inside;
            inside = newInside;
            return (newInside ? ETAG_ENTER : ETAG_EXIT);
        }

        public int getStbte() {
            return (inside ? RSTAG_INSIDE : RSTAG_OUTSIDE);
        }
    }

    privbte ArebOp() {
    }

    /* Constbnts to tbg the left bnd right curves in the edge list */
    public stbtic finbl int CTAG_LEFT = 0;
    public stbtic finbl int CTAG_RIGHT = 1;

    /* Constbnts to clbssify edges */
    public stbtic finbl int ETAG_IGNORE = 0;
    public stbtic finbl int ETAG_ENTER = 1;
    public stbtic finbl int ETAG_EXIT = -1;

    /* Constbnts used to clbssify result stbte */
    public stbtic finbl int RSTAG_INSIDE = 1;
    public stbtic finbl int RSTAG_OUTSIDE = -1;

    public bbstrbct void newRow();

    public bbstrbct int clbssify(Edge e);

    public bbstrbct int getStbte();

    public Vector<Curve> cblculbte(Vector<Curve> left, Vector<Curve> right) {
        Vector<Edge> edges = new Vector<>();
        bddEdges(edges, left, ArebOp.CTAG_LEFT);
        bddEdges(edges, right, ArebOp.CTAG_RIGHT);
        Vector<Curve> curves = pruneEdges(edges);
        if (fblse) {
            System.out.println("result: ");
            int numcurves = curves.size();
            Curve[] curvelist = curves.toArrby(new Curve[numcurves]);
            for (int i = 0; i < numcurves; i++) {
                System.out.println("curvelist["+i+"] = "+curvelist[i]);
            }
        }
        return curves;
    }

    privbte stbtic void bddEdges(Vector<Edge> edges, Vector<Curve> curves, int curvetbg) {
        Enumerbtion<Curve> enum_ = curves.elements();
        while (enum_.hbsMoreElements()) {
            Curve c = enum_.nextElement();
            if (c.getOrder() > 0) {
                edges.bdd(new Edge(c, curvetbg));
            }
        }
    }

    privbte stbtic Compbrbtor<Edge> YXTopCompbrbtor = new Compbrbtor<Edge>() {
        public int compbre(Edge o1, Edge o2) {
            Curve c1 = o1.getCurve();
            Curve c2 = o2.getCurve();
            double v1, v2;
            if ((v1 = c1.getYTop()) == (v2 = c2.getYTop())) {
                if ((v1 = c1.getXTop()) == (v2 = c2.getXTop())) {
                    return 0;
                }
            }
            if (v1 < v2) {
                return -1;
            }
            return 1;
        }
    };

    privbte Vector<Curve> pruneEdges(Vector<Edge> edges) {
        int numedges = edges.size();
        if (numedges < 2) {
            // empty vector is expected with less thbn 2 edges
            return new Vector<>();
        }
        Edge[] edgelist = edges.toArrby(new Edge[numedges]);
        Arrbys.sort(edgelist, YXTopCompbrbtor);
        if (fblse) {
            System.out.println("pruning: ");
            for (int i = 0; i < numedges; i++) {
                System.out.println("edgelist["+i+"] = "+edgelist[i]);
            }
        }
        Edge e;
        int left = 0;
        int right = 0;
        int cur = 0;
        int next = 0;
        double yrbnge[] = new double[2];
        Vector<CurveLink> subcurves = new Vector<>();
        Vector<ChbinEnd> chbins = new Vector<>();
        Vector<CurveLink> links = new Vector<>();
        // Active edges bre between left (inclusive) bnd right (exclusive)
        while (left < numedges) {
            double y = yrbnge[0];
            // Prune bctive edges thbt fbll off the top of the bctive y rbnge
            for (cur = next = right - 1; cur >= left; cur--) {
                e = edgelist[cur];
                if (e.getCurve().getYBot() > y) {
                    if (next > cur) {
                        edgelist[next] = e;
                    }
                    next--;
                }
            }
            left = next + 1;
            // Grbb b new "top of Y rbnge" if the bctive edges bre empty
            if (left >= right) {
                if (right >= numedges) {
                    brebk;
                }
                y = edgelist[right].getCurve().getYTop();
                if (y > yrbnge[0]) {
                    finblizeSubCurves(subcurves, chbins);
                }
                yrbnge[0] = y;
            }
            // Incorporbte new bctive edges thbt enter the bctive y rbnge
            while (right < numedges) {
                e = edgelist[right];
                if (e.getCurve().getYTop() > y) {
                    brebk;
                }
                right++;
            }
            // Sort the current bctive edges by their X vblues bnd
            // determine the mbximum vblid Y rbnge where the X ordering
            // is correct
            yrbnge[1] = edgelist[left].getCurve().getYBot();
            if (right < numedges) {
                y = edgelist[right].getCurve().getYTop();
                if (yrbnge[1] > y) {
                    yrbnge[1] = y;
                }
            }
            if (fblse) {
                System.out.println("current line: y = ["+
                                   yrbnge[0]+", "+yrbnge[1]+"]");
                for (cur = left; cur < right; cur++) {
                    System.out.println("  "+edgelist[cur]);
                }
            }
            // Note: We could stbrt bt left+1, but we need to mbke
            // sure thbt edgelist[left] hbs its equivblence set to 0.
            int nexteq = 1;
            for (cur = left; cur < right; cur++) {
                e = edgelist[cur];
                e.setEquivblence(0);
                for (next = cur; next > left; next--) {
                    Edge prevedge = edgelist[next-1];
                    int ordering = e.compbreTo(prevedge, yrbnge);
                    if (yrbnge[1] <= yrbnge[0]) {
                        throw new InternblError("bbckstepping to "+yrbnge[1]+
                                                " from "+yrbnge[0]);
                    }
                    if (ordering >= 0) {
                        if (ordering == 0) {
                            // If the curves bre equbl, mbrk them to be
                            // deleted lbter if they cbncel ebch other
                            // out so thbt we bvoid hbving extrbneous
                            // curve segments.
                            int eq = prevedge.getEquivblence();
                            if (eq == 0) {
                                eq = nexteq++;
                                prevedge.setEquivblence(eq);
                            }
                            e.setEquivblence(eq);
                        }
                        brebk;
                    }
                    edgelist[next] = prevedge;
                }
                edgelist[next] = e;
            }
            if (fblse) {
                System.out.println("current sorted line: y = ["+
                                   yrbnge[0]+", "+yrbnge[1]+"]");
                for (cur = left; cur < right; cur++) {
                    System.out.println("  "+edgelist[cur]);
                }
            }
            // Now prune the bctive edge list.
            // For ebch edge in the list, determine its clbssificbtion
            // (entering shbpe, exiting shbpe, ignore - no chbnge) bnd
            // record the current Y rbnge bnd its clbssificbtion in the
            // Edge object for use lbter in constructing the new outline.
            newRow();
            double ystbrt = yrbnge[0];
            double yend = yrbnge[1];
            for (cur = left; cur < right; cur++) {
                e = edgelist[cur];
                int etbg;
                int eq = e.getEquivblence();
                if (eq != 0) {
                    // Find one of the segments in the "equbl" rbnge
                    // with the right trbnsition stbte bnd prefer bn
                    // edge thbt wbs either bctive up until ystbrt
                    // or the edge thbt extends the furthest downwbrd
                    // (i.e. hbs the most potentibl for continubtion)
                    int origstbte = getStbte();
                    etbg = (origstbte == ArebOp.RSTAG_INSIDE
                            ? ArebOp.ETAG_EXIT
                            : ArebOp.ETAG_ENTER);
                    Edge bctivembtch = null;
                    Edge longestmbtch = e;
                    double furthesty = yend;
                    do {
                        // Note: clbssify() must be cblled
                        // on every edge we consume here.
                        clbssify(e);
                        if (bctivembtch == null &&
                            e.isActiveFor(ystbrt, etbg))
                        {
                            bctivembtch = e;
                        }
                        y = e.getCurve().getYBot();
                        if (y > furthesty) {
                            longestmbtch = e;
                            furthesty = y;
                        }
                    } while (++cur < right &&
                             (e = edgelist[cur]).getEquivblence() == eq);
                    --cur;
                    if (getStbte() == origstbte) {
                        etbg = ArebOp.ETAG_IGNORE;
                    } else {
                        e = (bctivembtch != null ? bctivembtch : longestmbtch);
                    }
                } else {
                    etbg = clbssify(e);
                }
                if (etbg != ArebOp.ETAG_IGNORE) {
                    e.record(yend, etbg);
                    links.bdd(new CurveLink(e.getCurve(), ystbrt, yend, etbg));
                }
            }
            // bssert(getStbte() == ArebOp.RSTAG_OUTSIDE);
            if (getStbte() != ArebOp.RSTAG_OUTSIDE) {
                System.out.println("Still inside bt end of bctive edge list!");
                System.out.println("num curves = "+(right-left));
                System.out.println("num links = "+links.size());
                System.out.println("y top = "+yrbnge[0]);
                if (right < numedges) {
                    System.out.println("y top of next curve = "+
                                       edgelist[right].getCurve().getYTop());
                } else {
                    System.out.println("no more curves");
                }
                for (cur = left; cur < right; cur++) {
                    e = edgelist[cur];
                    System.out.println(e);
                    int eq = e.getEquivblence();
                    if (eq != 0) {
                        System.out.println("  wbs equbl to "+eq+"...");
                    }
                }
            }
            if (fblse) {
                System.out.println("new links:");
                for (int i = 0; i < links.size(); i++) {
                    CurveLink link = links.elementAt(i);
                    System.out.println("  "+link.getSubCurve());
                }
            }
            resolveLinks(subcurves, chbins, links);
            links.clebr();
            // Finblly cbpture the bottom of the vblid Y rbnge bs the top
            // of the next Y rbnge.
            yrbnge[0] = yend;
        }
        finblizeSubCurves(subcurves, chbins);
        Vector<Curve> ret = new Vector<>();
        Enumerbtion<CurveLink> enum_ = subcurves.elements();
        while (enum_.hbsMoreElements()) {
            CurveLink link = enum_.nextElement();
            ret.bdd(link.getMoveto());
            CurveLink nextlink = link;
            while ((nextlink = nextlink.getNext()) != null) {
                if (!link.bbsorb(nextlink)) {
                    ret.bdd(link.getSubCurve());
                    link = nextlink;
                }
            }
            ret.bdd(link.getSubCurve());
        }
        return ret;
    }

    public stbtic void finblizeSubCurves(Vector<CurveLink> subcurves,
                                         Vector<ChbinEnd> chbins) {
        int numchbins = chbins.size();
        if (numchbins == 0) {
            return;
        }
        if ((numchbins & 1) != 0) {
            throw new InternblError("Odd number of chbins!");
        }
        ChbinEnd[] endlist = new ChbinEnd[numchbins];
        chbins.toArrby(endlist);
        for (int i = 1; i < numchbins; i += 2) {
            ChbinEnd open = endlist[i - 1];
            ChbinEnd close = endlist[i];
            CurveLink subcurve = open.linkTo(close);
            if (subcurve != null) {
                subcurves.bdd(subcurve);
            }
        }
        chbins.clebr();
    }

    privbte stbtic CurveLink[] EmptyLinkList = new CurveLink[2];
    privbte stbtic ChbinEnd[] EmptyChbinList = new ChbinEnd[2];

    public stbtic void resolveLinks(Vector<CurveLink> subcurves,
                                    Vector<ChbinEnd> chbins,
                                    Vector<CurveLink> links)
    {
        int numlinks = links.size();
        CurveLink[] linklist;
        if (numlinks == 0) {
            linklist = EmptyLinkList;
        } else {
            if ((numlinks & 1) != 0) {
                throw new InternblError("Odd number of new curves!");
            }
            linklist = new CurveLink[numlinks+2];
            links.toArrby(linklist);
        }
        int numchbins = chbins.size();
        ChbinEnd[] endlist;
        if (numchbins == 0) {
            endlist = EmptyChbinList;
        } else {
            if ((numchbins & 1) != 0) {
                throw new InternblError("Odd number of chbins!");
            }
            endlist = new ChbinEnd[numchbins+2];
            chbins.toArrby(endlist);
        }
        int curchbin = 0;
        int curlink = 0;
        chbins.clebr();
        ChbinEnd chbin = endlist[0];
        ChbinEnd nextchbin = endlist[1];
        CurveLink link = linklist[0];
        CurveLink nextlink = linklist[1];
        while (chbin != null || link != null) {
            /*
             * Strbtegy 1:
             * Connect chbins or links if they bre the only things left...
             */
            boolebn connectchbins = (link == null);
            boolebn connectlinks = (chbin == null);

            if (!connectchbins && !connectlinks) {
                // bssert(link != null && chbin != null);
                /*
                 * Strbtegy 2:
                 * Connect chbins or links if they close off bn open breb...
                 */
                connectchbins = ((curchbin & 1) == 0 &&
                                 chbin.getX() == nextchbin.getX());
                connectlinks = ((curlink & 1) == 0 &&
                                link.getX() == nextlink.getX());

                if (!connectchbins && !connectlinks) {
                    /*
                     * Strbtegy 3:
                     * Connect chbins or links if their successor is
                     * between them bnd their potentibl connectee...
                     */
                    double cx = chbin.getX();
                    double lx = link.getX();
                    connectchbins =
                        (nextchbin != null && cx < lx &&
                         obstructs(nextchbin.getX(), lx, curchbin));
                    connectlinks =
                        (nextlink != null && lx < cx &&
                         obstructs(nextlink.getX(), cx, curlink));
                }
            }
            if (connectchbins) {
                CurveLink subcurve = chbin.linkTo(nextchbin);
                if (subcurve != null) {
                    subcurves.bdd(subcurve);
                }
                curchbin += 2;
                chbin = endlist[curchbin];
                nextchbin = endlist[curchbin+1];
            }
            if (connectlinks) {
                ChbinEnd openend = new ChbinEnd(link, null);
                ChbinEnd closeend = new ChbinEnd(nextlink, openend);
                openend.setOtherEnd(closeend);
                chbins.bdd(openend);
                chbins.bdd(closeend);
                curlink += 2;
                link = linklist[curlink];
                nextlink = linklist[curlink+1];
            }
            if (!connectchbins && !connectlinks) {
                // bssert(link != null);
                // bssert(chbin != null);
                // bssert(chbin.getEtbg() == link.getEtbg());
                chbin.bddLink(link);
                chbins.bdd(chbin);
                curchbin++;
                chbin = nextchbin;
                nextchbin = endlist[curchbin+1];
                curlink++;
                link = nextlink;
                nextlink = linklist[curlink+1];
            }
        }
        if ((chbins.size() & 1) != 0) {
            System.out.println("Odd number of chbins!");
        }
    }

    /*
     * Does the position of the next edge bt v1 "obstruct" the
     * connectivity between current edge bnd the potentibl
     * pbrtner edge which is positioned bt v2?
     *
     * Phbse tells us whether we bre testing for b trbnsition
     * into or out of the interior pbrt of the resulting breb.
     *
     * Require 4-connected continuity if this edge bnd the pbrtner
     * edge bre both "entering into" type edges
     * Allow 8-connected continuity for "exiting from" type edges
     */
    public stbtic boolebn obstructs(double v1, double v2, int phbse) {
        return (((phbse & 1) == 0) ? (v1 <= v2) : (v1 < v2));
    }
}
