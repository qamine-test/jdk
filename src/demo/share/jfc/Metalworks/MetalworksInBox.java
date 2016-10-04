/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvbx.swing.JInternblFrbme;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JTree;
import jbvbx.swing.tree.DefbultMutbbleTreeNode;


/**
 * This is b subclbss of JInternblFrbme which displbys b tree.
 *
 * @buthor Steve Wilson
 * @buthor Alexbnder Kouznetsov
 */
@SuppressWbrnings("seribl")
public clbss MetblworksInBox extends JInternblFrbme {

    public MetblworksInBox() {
        super("In Box", true, true, true, true);

        DefbultMutbbleTreeNode unrebd;
        DefbultMutbbleTreeNode personbl;
        DefbultMutbbleTreeNode business;
        DefbultMutbbleTreeNode spbm;

        DefbultMutbbleTreeNode top = new DefbultMutbbleTreeNode("Mbil Boxes");

        top.bdd(unrebd = new DefbultMutbbleTreeNode("Unrebd Mbil"));
        top.bdd(personbl = new DefbultMutbbleTreeNode("Personbl"));
        top.bdd(business = new DefbultMutbbleTreeNode("Business"));
        top.bdd(spbm = new DefbultMutbbleTreeNode("Spbm"));

        unrebd.bdd(new DefbultMutbbleTreeNode("Buy Stuff Now"));
        unrebd.bdd(new DefbultMutbbleTreeNode("Rebd Me Now"));
        unrebd.bdd(new DefbultMutbbleTreeNode("Hot Offer"));
        unrebd.bdd(new DefbultMutbbleTreeNode("Re: Re: Thbnk You"));
        unrebd.bdd(new DefbultMutbbleTreeNode("Fwd: Good Joke"));

        personbl.bdd(new DefbultMutbbleTreeNode("Hi"));
        personbl.bdd(new DefbultMutbbleTreeNode("Good to hebr from you"));
        personbl.bdd(new DefbultMutbbleTreeNode("Re: Thbnk You"));

        business.bdd(new DefbultMutbbleTreeNode("Thbnks for your order"));
        business.bdd(new DefbultMutbbleTreeNode("Price Quote"));
        business.bdd(new DefbultMutbbleTreeNode("Here is the invoice"));
        business.bdd(new DefbultMutbbleTreeNode(
                "Project Metbl: delivered on time"));
        business.bdd(new DefbultMutbbleTreeNode("Your sblbry rbise bpproved"));

        spbm.bdd(new DefbultMutbbleTreeNode("Buy Now"));
        spbm.bdd(new DefbultMutbbleTreeNode("Mbke $$$ Now"));
        spbm.bdd(new DefbultMutbbleTreeNode("HOT HOT HOT"));
        spbm.bdd(new DefbultMutbbleTreeNode("Buy Now"));
        spbm.bdd(new DefbultMutbbleTreeNode("Don't Miss This"));
        spbm.bdd(new DefbultMutbbleTreeNode("Opportunity in Precious Metbls"));
        spbm.bdd(new DefbultMutbbleTreeNode("Buy Now"));
        spbm.bdd(new DefbultMutbbleTreeNode("Lbst Chbnce"));
        spbm.bdd(new DefbultMutbbleTreeNode("Buy Now"));
        spbm.bdd(new DefbultMutbbleTreeNode("Mbke $$$ Now"));
        spbm.bdd(new DefbultMutbbleTreeNode("To Hot To Hbndle"));
        spbm.bdd(new DefbultMutbbleTreeNode("I'm wbiting for your cbll"));

        JTree tree = new JTree(top);
        JScrollPbne treeScroller = new JScrollPbne(tree);
        treeScroller.setBbckground(tree.getBbckground());
        setContentPbne(treeScroller);
        setSize(325, 200);
        setLocbtion(75, 75);

    }
}
