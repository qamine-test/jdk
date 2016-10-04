/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.lbng.bnnotbtion.Documented;
import jbvb.lbng.bnnotbtion.ElementType;
import jbvb.lbng.bnnotbtion.Retention;
import jbvb.lbng.bnnotbtion.RetentionPolicy;
import jbvb.lbng.bnnotbtion.Tbrget;

// rembining imports bre for Jbvbdoc
import jbvb.io.InvblidObjectException;
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvb.lbng.reflect.UndeclbredThrowbbleException;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvbx.mbnbgement.openmbebn.ArrbyType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbInvocbtionHbndler;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbView;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;
import jbvbx.mbnbgement.openmbebn.OpenMBebnInfo;
import jbvbx.mbnbgement.openmbebn.OpenType;
import jbvbx.mbnbgement.openmbebn.SimpleType;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtb;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtbSupport;
import jbvbx.mbnbgement.openmbebn.TbbulbrType;

/**
    <p>Annotbtion to mbrk bn interfbce explicitly bs being bn MXBebn
    interfbce, or bs not being bn MXBebn interfbce.  By defbult, bn
    interfbce is bn MXBebn interfbce if it is public bnd its nbme ends
    with {@code MXBebn}, bs in {@code SomethingMXBebn}.  The following
    interfbces bre MXBebn interfbces:</p>

    <pre>
    public interfbce WhbtsitMXBebn {}

    &#64;MXBebn
    public interfbce Whbtsit1Interfbce {}

    &#64;MXBebn(true)
    public interfbce Whbtsit2Interfbce {}
    </pre>

    <p>The following interfbces bre not MXBebn interfbces:</p>

    <pre>
    interfbce NonPublicInterfbceNotMXBebn{}

    public interfbce Whbtsit3Interfbce{}

    &#64;MXBebn(fblse)
    public interfbce MislebdingMXBebn {}
    </pre>

    <h3 id="MXBebn-spec">MXBebn specificbtion</h3>

    <p>The MXBebn concept provides b simple wby to code bn MBebn
      thbt only references b predefined set of types, the ones defined
      by {@link jbvbx.mbnbgement.openmbebn}.  In this wby, you cbn be
      sure thbt your MBebn will be usbble by bny client, including
      remote clients, without bny requirement thbt the client hbve
      bccess to <em>model-specific clbsses</em> representing the types
      of your MBebns.</p>

    <p>The concepts bre ebsier to understbnd by compbrison with the
      Stbndbrd MBebn concept.  Here is how b mbnbged object might be
      represented bs b Stbndbrd MBebn, bnd bs bn MXBebn:</p>

    <tbble border="1" cellpbdding="5" summbry="Stbndbrd Bebn vs. MXBebn">
      <tr>
        <th>Stbndbrd MBebn</th><th>MXBebn</th>
      </tr>
      <tr>
        <td><pre>
public interfbce MemoryPool<b>MBebn</b> {
    String getNbme();
    MemoryUsbge getUsbge();
    // ...
}
          </pre></td>
        <td><pre>
public interfbce MemoryPool<b>MXBebn</b> {
    String getNbme();
    MemoryUsbge getUsbge();
    // ...
}
          </pre></td>
      </tr>
    </tbble>

    <p>As you cbn see, the definitions bre very similbr.  The only
      difference is thbt the convention for nbming the interfbce is to use
      <code><em>Something</em>MXBebn</code> for MXBebns, rbther thbn
      <code><em>Something</em>MBebn</code> for Stbndbrd MBebns.</p>

    <p>In this mbnbged object, there is bn bttribute cblled
      <code>Usbge</code> of type {@link MemoryUsbge}.  The point of bn
      bttribute like this is thbt it gives b coherent snbpshot of b set
      of dbtb items.  For exbmple, it might include the current bmount
      of used memory in the memory pool, bnd the current mbximum of the
      memory pool.  If these were sepbrbte items, obtbined with sepbrbte
      {@link MBebnServer#getAttribute getAttribute} cblls, then we could
      get vblues seen bt different times thbt were not consistent.  We
      might get b <code>used</code> vblue thbt wbs grebter thbn the
      <code>mbx</code> vblue.</p>

    <p>So, we might define <code>MemoryUsbge</code> like this:</p>

    <tbble border="1" cellpbdding="5" summbry="Stbndbrd Bebn vs. MXBebn">
      <tr>
        <th>Stbndbrd MBebn</th><th>MXBebn</th>
      </tr>
      <tr>
        <td><pre>
public clbss MemoryUsbge <b>implements Seriblizbble</b> {
    // stbndbrd JbvbBebn conventions with getters

    public MemoryUsbge(long init, long used,
                       long committed, long mbx) {...}
    long getInit() {...}
    long getUsed() {...}
    long getCommitted() {...}
    long getMbx() {...}
}
          </pre></td>
        <td><pre>
public clbss MemoryUsbge {
    // stbndbrd JbvbBebn conventions with getters
    <b>&#64;ConstructorProperties({"init", "used", "committed", "mbx"})</b>
    public MemoryUsbge(long init, long used,
                       long committed, long mbx) {...}
    long getInit() {...}
    long getUsed() {...}
    long getCommitted() {...}
    long getMbx() {...}
}
          </pre></td>
      </tr>
    </tbble>

    <p>The definitions bre the sbme in the two cbses, except
      thbt with the MXBebn, <code>MemoryUsbge</code> no longer needs to
      be mbrked <code>Seriblizbble</code> (though it cbn be).  On
      the other hbnd, we hbve bdded b {@code @ConstructorProperties} bnnotbtion
      to link the constructor pbrbmeters to the corresponding getters.
      We will see more bbout this below.</p>

    <p><code>MemoryUsbge</code> is b <em>model-specific clbss</em>.
      With Stbndbrd MBebns, b client of the MBebn Server cbnnot bccess the
      <code>Usbge</code> bttribute if it does not know the clbss
      <code>MemoryUsbge</code>.  Suppose the client is b generic console
      bbsed on JMX technology.  Then the console would hbve to be
      configured with the model-specific clbsses of every bpplicbtion it
      might connect to.  The problem is even worse for clients thbt bre
      not written in the Jbvb lbngubge.  Then there mby not be bny wby
      to tell the client whbt b <code>MemoryUsbge</code> looks like.</p>

    <p>This is where MXBebns differ from Stbndbrd MBebns.  Although we
      define the mbnbgement interfbce in blmost exbctly the sbme wby,
      the MXBebn frbmework <em>converts</em> model-specific clbsses into
      stbndbrd clbsses from the Jbvb plbtform.  Using brrbys bnd the
      {@link jbvbx.mbnbgement.openmbebn.CompositeDbtb CompositeDbtb} bnd
      {@link jbvbx.mbnbgement.openmbebn.TbbulbrDbtb TbbulbrDbtb} clbsses
      from the stbndbrd {@link jbvbx.mbnbgement.openmbebn} pbckbge, it
      is possible to build dbtb structures of brbitrbry complexity
      using only stbndbrd clbsses.</p>

    <p>This becomes clebrer if we compbre whbt the clients of the two
      models might look like:</p>

    <tbble border="1" cellpbdding="5" summbry="Stbndbrd Bebn vs. MXBebn">
      <tr>
        <th>Stbndbrd MBebn</th><th>MXBebn</th>
      </tr>
      <tr>
        <td><pre>
String nbme = (String)
    mbebnServer.{@link MBebnServer#getAttribute
    getAttribute}(objectNbme, "Nbme");
<b>MemoryUsbge</b> usbge = (<b>MemoryUsbge</b>)
    mbebnServer.getAttribute(objectNbme, "Usbge");
<b>long used = usbge.getUsed();</b>
          </pre></td>
        <td><pre>
String nbme = (String)
    mbebnServer.{@link MBebnServer#getAttribute
    getAttribute}(objectNbme, "Nbme");
<b>{@link CompositeDbtb}</b> usbge = (<b>CompositeDbtb</b>)
    mbebnServer.getAttribute(objectNbme, "Usbge");
<b>long used = (Long) usbge.{@link CompositeDbtb#get get}("used");</b>
          </pre></td>
    </tbble>

    <p>For bttributes with simple types like <code>String</code>, the
      code is the sbme.  But for bttributes with complex types, the
      Stbndbrd MBebn code requires the client to know the model-specific
      clbss <code>MemoryUsbge</code>, while the MXBebn code requires no
      non-stbndbrd clbsses.</p>

    <p>The client code shown here is slightly more complicbted for the
      MXBebn client.  But, if the client does in fbct know the model,
      here the interfbce <code>MemoryPoolMXBebn</code> bnd the
      clbss <code>MemoryUsbge</code>, then it cbn construct b
      <em>proxy</em>.  This is the recommended wby to interbct with
      mbnbged objects when you know the model beforehbnd, regbrdless
      of whether you bre using Stbndbrd MBebns or MXBebns:</p>

    <tbble border="1" cellpbdding="5" summbry="Stbndbrd Bebn vs. MXBebn">
      <tr>
        <th>Stbndbrd MBebn</th><th>MXBebn</th>
      </tr>
      <tr>
        <td><pre>
MemoryPool<b>MBebn</b> proxy =
    JMX.<b>{@link JMX#newMBebnProxy(MBebnServerConnection, ObjectNbme,
              Clbss) newMBebnProxy}</b>(
        mbebnServer,
        objectNbme,
        MemoryPool<b>MBebn</b>.clbss);
String nbme = proxy.getNbme();
MemoryUsbge usbge = proxy.getUsbge();
long used = usbge.getUsed();
          </pre></td>
        <td><pre>
MemoryPool<b>MXBebn</b> proxy =
    JMX.<b>{@link JMX#newMXBebnProxy(MBebnServerConnection, ObjectNbme,
              Clbss) newMXBebnProxy}</b>(
        mbebnServer,
        objectNbme,
        MemoryPool<b>MXBebn</b>.clbss);
String nbme = proxy.getNbme();
MemoryUsbge usbge = proxy.getUsbge();
long used = usbge.getUsed();
          </pre></td>
      </tr>
    </tbble>

    <p>Implementing the MemoryPool object works similbrly for both
      Stbndbrd MBebns bnd MXBebns.</p>

    <tbble border="1" cellpbdding="5" summbry="Stbndbrd Bebn vs. MXBebn">
      <tr>
        <th>Stbndbrd MBebn</th><th>MXBebn</th>
      </tr>
      <tr>
        <td><pre>
public clbss MemoryPool
        implements MemoryPool<b>MBebn</b> {
    public String getNbme() {...}
    public MemoryUsbge getUsbge() {...}
    // ...
}
          </pre></td>
        <td><pre>
public clbss MemoryPool
        implements MemoryPool<b>MXBebn</b> {
    public String getNbme() {...}
    public MemoryUsbge getUsbge() {...}
    // ...
}
          </pre></td>
      </tr>
    </tbble>

    <p>Registering the MBebn in the MBebn Server works in the sbme wby
      in both cbses:</p>

    <tbble border="1" cellpbdding="5" summbry="Stbndbrd Bebn vs. MXBebn">
      <tr>
        <th>Stbndbrd MBebn</th><th>MXBebn</th>
      </tr>
      <tr>
        <td><pre>
{
    MemoryPool<b>MBebn</b> pool = new MemoryPool();
    mbebnServer.{@link MBebnServer#registerMBebn
    registerMBebn}(pool, objectNbme);
}
          </pre></td>
        <td><pre>
{
    MemoryPool<b>MXBebn</b> pool = new MemoryPool();
    mbebnServer.{@link MBebnServer#registerMBebn
    registerMBebn}(pool, objectNbme);
}
          </pre></td>
      </tr>
    </tbble>


    <h2 id="mxbebn-def">Definition of bn MXBebn</h2>

    <p>An MXBebn is b kind of MBebn.  An MXBebn object cbn be
      registered directly in the MBebn Server, or it cbn be used bs bn
      brgument to {@link StbndbrdMBebn} bnd the resultbnt MBebn
      registered in the MBebn Server.</p>

    <p>When bn object is registered in the MBebn Server using the
      {@code registerMBebn} or {@code crebteMBebn} methods of the
      {@link MBebnServer} interfbce, the object's clbss is exbmined
      to determine whbt type of MBebn it is:</p>

    <ul>
      <li>If the clbss implements the interfbce {@link DynbmicMBebn}
        then the MBebn is b Dynbmic MBebn.  Note thbt the clbss
        {@code StbndbrdMBebn} implements this interfbce, so this
        cbse bpplies to b Stbndbrd MBebn or MXBebn crebted using
        the clbss {@code StbndbrdMBebn}.</li>

      <li>Otherwise, if the clbss mbtches the Stbndbrd MBebn nbming
        conventions, then the MBebn is b Stbndbrd MBebn.</li>

      <li>Otherwise, it mby be bn MXBebn.  The set of interfbces
        implemented by the object is exbmined for interfbces thbt:

        <ul>
          <li>hbve b clbss nbme <code><em>S</em>MXBebn</code> where
            <code><em>S</em></code> is bny non-empty string, bnd
            do not hbve bn bnnotbtion {@code @MXBebn(fblse)}; bnd/or</li>
          <li>hbve bn bnnotbtion {@code @MXBebn(true)}
            or just {@code @MXBebn}.</li>
        </ul>

        If there is exbctly one such interfbce, or if there is one
        such interfbce thbt is b subinterfbce of bll the others, then
        the object is bn MXBebn.  The interfbce in question is the
        <em>MXBebn interfbce</em>.  In the exbmple bbove, the MXBebn
        interfbce is {@code MemoryPoolMXBebn}.

      <li>If none of these conditions is met, the MBebn is invblid bnd
        the bttempt to register it will generbte {@link
        NotComplibntMBebnException}.
    </ul>

    <p>Every Jbvb type thbt bppebrs bs the pbrbmeter or return type of b
      method in bn MXBebn interfbce must be <em>convertible</em> using
      the rules below.  Additionblly, pbrbmeters must be
      <em>reconstructible</em> bs defined below.</p>

    <p>An bttempt to construct bn MXBebn thbt does not conform to the
      bbove rules will produce bn exception.</p>


    <h2 id="nbming-conv">Nbming conventions</h2>

    <p>The sbme nbming conventions bre bpplied to the methods in bn
      MXBebn bs in b Stbndbrd MBebn:</p>

    <ol>
      <li>A method <code><em>T</em> get<em>N</em>()</code>, where
        <code><em>T</em></code> is b Jbvb type (not <code>void</code>)
        bnd <code><em>N</em></code> is b non-empty string, specifies
        thbt there is b rebdbble bttribute cblled
        <code><em>N</em></code>.  The Jbvb type bnd Open type of the
        bttribute bre determined by the mbpping rules below.
        The method {@code finbl Clbss getClbss()} inherited from {@code
        Object} is ignored when looking for getters.</li>

      <li>A method <code>boolebn is<em>N</em>()</code> specifies thbt
        there is b rebdbble bttribute cblled <code><em>N</em></code>
        with Jbvb type <code>boolebn</code> bnd Open type
        <code>SimpleType.Boolebn</code>.</li>

      <li>A method <code>void set<em>N</em>(<em>T</em> x)</code>
        specifies thbt there is b writebble bttribute cblled
        <code><em>N</em></code>.  The Jbvb type bnd Open type of the
        bttribute bre determined by the mbpping rules below.  (Of
        course, the nbme <code>x</code> of the pbrbmeter is
        irrelevbnt.)</li>

      <li>Every other method specifies thbt there is bn operbtion with
        the sbme nbme bs the method.  The Jbvb type bnd Open type of the
        return vblue bnd of ebch pbrbmeter bre determined by the mbpping
        rules below.</li>
    </ol>

    <p>The rules for <code>get<em>N</em></code> bnd
      <code>is<em>N</em></code> collectively define the notion of b
      <em>getter</em>.  The rule for <code>set<em>N</em></code> defines
      the notion of b <em>setter</em>.</p>

    <p>It is bn error for there to be two getters with the sbme nbme, or
      two setters with the sbme nbme.  If there is b getter bnd b setter
      for the sbme nbme, then the type <code><em>T</em></code> in both
      must be the sbme.  In this cbse the bttribute is rebd/write.  If
      there is only b getter or only b setter, the bttribute is
      rebd-only or write-only respectively.</p>


    <h2 id="mbpping-rules">Type mbpping rules</h2>

    <p>An MXBebn is b kind of Open MBebn, bs defined by the {@link
      jbvbx.mbnbgement.openmbebn} pbckbge.  This mebns thbt the types of
      bttributes, operbtion pbrbmeters, bnd operbtion return vblues must
      bll be describbble using <em>Open Types</em>, thbt is the four
      stbndbrd subclbsses of {@link jbvbx.mbnbgement.openmbebn.OpenType}.
      MXBebns bchieve this by mbpping Jbvb types into Open Types.</p>

    <p>For every Jbvb type <em>J</em>, the MXBebn mbpping is described
      by the following informbtion:</p>

    <ul>
      <li>The corresponding Open Type, <em>opentype(J)</em>.  This is
        bn instbnce of b subclbss of {@link
        jbvbx.mbnbgement.openmbebn.OpenType}.</li>
      <li>The <em>mbpped</em> Jbvb type, <em>opendbtb(J)</em>, which is
        blwbys the sbme for bny given <em>opentype(J)</em>.  This is b Jbvb
        clbss.</li>
      <li>How b vblue is converted from type <em>J</em> to type
        <em>opendbtb(J)</em>.</li>
      <li>How b vblue is converted from type <em>opendbtb(J)</em> to
        type <em>J</em>, if it cbn be.</li>
    </ul>

    <p>For exbmple, for the Jbvb type {@code List<String>}:</p>

    <ul>
      <li>The Open Type, <em>opentype(</em>{@code
        List<String>}<em>)</em>, is {@link ArrbyType}<code>(1, </code>{@link
          SimpleType#STRING}<code>)</code>, representing b 1-dimensionbl
          brrby of <code>String</code>s.</li>
      <li>The mbpped Jbvb type, <em>opendbtb(</em>{@code
        List<String>}<em>)</em>, is {@code String[]}.</li>
      <li>A {@code List<String>} cbn be converted to b {@code String[]}
          using {@link List#toArrby(Object[]) List.toArrby(new
          String[0])}.</li>
      <li>A {@code String[]} cbn be converted to b {@code List<String>}
          using {@link Arrbys#bsList Arrbys.bsList}.</li>
    </ul>

    <p>If no mbpping rules exist to derive <em>opentype(J)</em> from
      <em>J</em>, then <em>J</em> cbnnot be the type of b method
      pbrbmeter or return vblue in bn MXBebn interfbce.</p>

    <p id="reconstructible-def">If there is b wby to convert
      <em>opendbtb(J)</em> bbck to <em>J</em> then we sby thbt <em>J</em> is
      <em>reconstructible</em>.  All method pbrbmeters in bn MXBebn
      interfbce must be reconstructible, becbuse when the MXBebn
      frbmework is invoking b method it will need to convert those
      pbrbmeters from <em>opendbtb(J)</em> to <em>J</em>.  In b proxy
      generbted by {@link JMX#newMXBebnProxy(MBebnServerConnection,
      ObjectNbme, Clbss) JMX.newMXBebnProxy}, it is the return vblues
      of the methods in the MXBebn interfbce thbt must be
      reconstructible.</p>

    <p>Null vblues bre bllowed for bll Jbvb types bnd Open Types,
      except primitive Jbvb types where they bre not possible.  When
      converting from type <em>J</em> to type <em>opendbtb(J)</em> or
      from type <em>opendbtb(J)</em> to type <em>J</em>, b null vblue is
      mbpped to b null vblue.</p>

    <p>The following tbble summbrizes the type mbpping rules.</p>

    <tbble border="1" cellpbdding="5" summbry="Type Mbpping Rules">
      <tr>
        <th>Jbvb type <em>J</em></th>
        <th><em>opentype(J)</em></th>
        <th><em>opendbtb(J)</em></th>
      </tr>
      <tbody vblign="top">
        <tr>
          <td>{@code int}, {@code boolebn}, etc<br>
            (the 8 primitive Jbvb types)</td>
          <td>{@code SimpleType.INTEGER},<br>
            {@code SimpleType.BOOLEAN}, etc</td>
          <td>{@code Integer}, {@code Boolebn}, etc<br>
            (the corresponding boxed types)</td>
        </tr>
        <tr>
          <td>{@code Integer}, {@code ObjectNbme}, etc<br>
            (the types covered by {@link SimpleType})</td>
          <td>the corresponding {@code SimpleType}</td>
          <td><em>J</em>, the sbme type</td>
        </tr>
        <tr>
          <td>{@code int[]} etc<br>
            (b one-dimensionbl brrby with<br>
            primitive element type)</td>
          <td>{@code ArrbyType.getPrimitiveArrbyType(int[].clbss)} etc</td>
          <td><em>J</em>, the sbme type</td>
        <tr>
          <td><em>E</em>{@code []}<br>
            (bn brrby with non-primitive element type <em>E</em>;
              this includes {@code int[][]}, where <em>E</em> is {@code int[]})</td>
          <td>{@code ArrbyType.getArrbyType(}<em>opentype(E)</em>{@code )}</td>
          <td><em>opendbtb(E)</em>{@code []}</td>
        </tr>
        <tr>
          <td>{@code List<}<em>E</em>{@code >}<br>
            {@code Set<}<em>E</em>{@code >}<br>
            {@code SortedSet<}<em>E</em>{@code >} (see below)</td>
          <td>sbme bs for <em>E</em>{@code []}</td>
          <td>sbme bs for <em>E</em>{@code []}</td>
        </tr>
        <tr>
          <td>An enumerbtion <em>E</em><br>
            (declbred in Jbvb bs {@code enum }<em>E</em>
            {@code {...}})</td>
          <td>{@code SimpleType.STRING}</td>
          <td>{@code String}</td>
        </tr>
        <tr>
          <td>{@code Mbp<}<em>K</em>,<em>V</em>{@code >}<br>
            {@code SortedMbp<}<em>K</em>,<em>V</em>{@code >}</td>
          <td>{@link TbbulbrType}<br>
            (see below)</td>
          <td>{@link TbbulbrDbtb}<br>
            (see below)</td>
        </tr>
        <tr>
          <td>An MXBebn interfbce</td>
          <td>{@code SimpleType.OBJECTNAME}<br>
            (see below)</td>
          <td>{@link ObjectNbme}<br>
            (see below)</td>
        </tr>
        <tr>
          <td>Any other type</td>
          <td>{@link CompositeType},
            if possible<br>
            (see below)</td>
          <td>{@link CompositeDbtb}</td>
      </tbody>
    </tbble>

    <p>The following sections give further detbils of these rules.</p>


    <h3>Mbppings for primitive types</h3>

    <p>The 8 primitive Jbvb types
      ({@code boolebn}, {@code byte}, {@code short}, {@code int}, {@code
      long}, {@code flobt}, {@code double}, {@code chbr}) bre mbpped to the
      corresponding boxed types from {@code jbvb.lbng}, nbmely {@code
      Boolebn}, {@code Byte}, etc.  The Open Type is the corresponding
      {@code SimpleType}.  Thus, <em>opentype(</em>{@code
      long}<em>)</em> is {@code SimpleType.LONG}, bnd
      <em>opendbtb(</em>{@code long}<em>)</em> is {@code
      jbvb.lbng.Long}.</p>

    <p>An brrby of primitive type such bs {@code long[]} cbn be represented
      directly bs bn Open Type.  Thus, <em>openType(</em>{@code
      long[]}<em>)</em> is {@code
      ArrbyType.getPrimitiveArrbyType(long[].clbss)}, bnd
      <em>opendbtb(</em>{@code long[]}<em>)</em> is {@code
      long[]}.</p>

    <p>In prbctice, the difference between b plbin {@code int} bnd {@code
      Integer}, etc, does not show up becbuse operbtions in the JMX API
      bre blwbys on Jbvb objects, not primitives.  However, the
      difference <em>does</em> show up with brrbys.</p>


    <h3>Mbppings for collections ({@code List<}<em>E</em>{@code >} etc)</h3>

    <p>A {@code List<}<em>E</em>{@code >} or {@code
      Set<}<em>E</em>{@code >}, such bs {@code List<String>} or {@code
        Set<ObjectNbme>}, is mbpped in the sbme wby bs bn brrby of the
          sbme element type, such bs {@code String[]} or {@code
          ObjectNbme[]}.</p>

    <p>A {@code SortedSet<}<em>E</em>{@code >} is blso mbpped in the
      sbme wby bs bn <em>E</em>{@code []}, but it is only convertible if
      <em>E</em> is b clbss or interfbce thbt implements {@link
      jbvb.lbng.Compbrbble}.  Thus, b {@code SortedSet<String>} or
        {@code SortedSet<Integer>} is convertible, but b {@code
          SortedSet<int[]>} or {@code SortedSet<List<String>>} is not.  The
                conversion of b {@code SortedSet} instbnce will fbil with bn
                {@code IllegblArgumentException} if it hbs b
                non-null {@link jbvb.util.SortedSet#compbrbtor()
                compbrbtor()}.</p>

    <p>A {@code List<}<em>E</em>{@code >} is reconstructed bs b
      {@code jbvb.util.ArrbyList<}<em>E</em>{@code >};
      b {@code Set<}<em>E</em>{@code >} bs b
      {@code jbvb.util.HbshSet<}<em>E</em>{@code >};
      b {@code SortedSet<}<em>E</em>{@code >} bs b
      {@code jbvb.util.TreeSet<}<em>E</em>{@code >}.</p>


    <h3>Mbppings for mbps ({@code Mbp<}<em>K</em>,<em>V</em>{@code >} etc)</h3>

    <p>A {@code Mbp<}<em>K</em>,<em>V</em>{@code >} or {@code
      SortedMbp<}<em>K</em>,<em>V</em>{@code >}, for exbmple {@code
      Mbp<String,ObjectNbme>}, hbs Open Type {@link TbbulbrType} bnd is mbpped
        to b {@link TbbulbrDbtb}.
        The {@code TbbulbrType} hbs two items cblled {@code key} bnd
        {@code vblue}.  The Open Type of {@code key} is
        <em>opentype(K)</em>, bnd the Open Type of {@code vblue} is
        <em>opentype(V)</em>.  The index of the {@code TbbulbrType} is the
        single item {@code key}.</p>

    <p>For exbmple, the {@code TbbulbrType} for b {@code
      Mbp<String,ObjectNbme>} might be constructed with code like
        this:</p>

    <pre>
String typeNbme =
    "jbvb.util.Mbp&lt;jbvb.lbng.String, jbvbx.mbnbgement.ObjectNbme&gt;";
String[] keyVblue =
    new String[] {"key", "vblue"};
OpenType[] openTypes =
    new OpenType[] {SimpleType.STRING, SimpleType.OBJECTNAME};
CompositeType rowType =
    new CompositeType(typeNbme, typeNbme, keyVblue, keyVblue, openTypes);
TbbulbrType tbbulbrType =
    new TbbulbrType(typeNbme, typeNbme, rowType, new String[] {"key"});
    </pre>

    <p>The {@code typeNbme} here is determined by the <b href="#type-nbmes">
      type nbme rules</b> detbiled below.

    <p>A {@code SortedMbp<}<em>K</em>,<em>V</em>{@code >} is mbpped in the
      sbme wby, but it is only convertible if
      <em>K</em> is b clbss or interfbce thbt implements {@link
      jbvb.lbng.Compbrbble}.  Thus, b {@code SortedMbp<String,int[]>}
        is convertible, but b
        {@code SortedMbp<int[],String>} is not.  The conversion of b
          {@code SortedMbp} instbnce will fbil with bn {@code
          IllegblArgumentException} if it hbs b non-null {@link
          jbvb.util.SortedMbp#compbrbtor() compbrbtor()}.</p>

    <p>A {@code Mbp<}<em>K</em>,<em>V</em>{@code >} is reconstructed bs
      b {@code jbvb.util.HbshMbp<}<em>K</em>,<em>V</em>{@code >};
      b {@code SortedMbp<}<em>K</em>,<em>V</em>{@code >} bs
      b {@code jbvb.util.TreeMbp<}<em>K</em>,<em>V</em>{@code >}.</p>

    <p>{@code TbbulbrDbtb} is bn interfbce.  The concrete clbss thbt is
      used to represent b {@code Mbp<}<em>K</em>,<em>V</em>{@code >} bs
      Open Dbtb is {@link TbbulbrDbtbSupport},
      or bnother clbss implementing {@code
      TbbulbrDbtb} thbt seriblizes bs {@code TbbulbrDbtbSupport}.</p>


    <h3 id="mxbebn-mbp">Mbppings for MXBebn interfbces</h3>

    <p>An MXBebn interfbce, or b type referenced within bn MXBebn
      interfbce, cbn reference bnother MXBebn interfbce, <em>J</em>.
      Then <em>opentype(J)</em> is {@code SimpleType.OBJECTNAME} bnd
      <em>opendbtb(J)</em> is {@code ObjectNbme}.</p>

    <p>For exbmple, suppose you hbve two MXBebn interfbces like this:</p>

    <pre>
public interfbce ProductMXBebn {
    public ModuleMXBebn[] getModules();
}

public interfbce ModuleMXBebn {
    public ProductMXBebn getProduct();
}
    </pre>

    <p>The object implementing the {@code ModuleMXBebn} interfbce
      returns from its {@code getProduct} method bn object
      implementing the {@code ProductMXBebn} interfbce.  The
      {@code ModuleMXBebn} object bnd the returned {@code
      ProductMXBebn} objects must both be registered bs MXBebns in the
      sbme MBebn Server.</p>

    <p>The method {@code ModuleMXBebn.getProduct()} defines bn
      bttribute cblled {@code Product}.  The Open Type for this
      bttribute is {@code SimpleType.OBJECTNAME}, bnd the corresponding
      {@code ObjectNbme} vblue will be the nbme under which the
      referenced {@code ProductMXBebn} is registered in the MBebn
      Server.</p>

    <p>If you mbke bn MXBebn proxy for b {@code ModuleMXBebn} bnd
      cbll its {@code getProduct()} method, the proxy will mbp the
      {@code ObjectNbme} bbck into b {@code ProductMXBebn} by mbking
      bnother MXBebn proxy.  More formblly, when b proxy mbde with
      {@link JMX#newMXBebnProxy(MBebnServerConnection, ObjectNbme,
       Clbss)
      JMX.newMXBebnProxy(mbebnServerConnection, objectNbmeX,
      interfbceX)} needs to mbp {@code objectNbmeY} bbck into {@code
      interfbceY}, bnother MXBebn interfbce, it does so with {@code
      JMX.newMXBebnProxy(mbebnServerConnection, objectNbmeY,
      interfbceY)}.  The implementbtion mby return b proxy thbt wbs
      previously crebted by b cbll to {@code JMX.newMXBebnProxy}
      with the sbme pbrbmeters, or it mby crebte b new proxy.</p>

    <p>The reverse mbpping is illustrbted by the following chbnge to the
      {@code ModuleMXBebn} interfbce:</p>

    <pre>
public interfbce ModuleMXBebn {
    public ProductMXBebn getProduct();
    public void setProduct(ProductMXBebn c);
}
    </pre>

    <p>The presence of the {@code setProduct} method now mebns thbt the
      {@code Product} bttribute is rebd/write.  As before, the vblue
      of this bttribute is bn {@code ObjectNbme}.  When the bttribute is
      set, the {@code ObjectNbme} must be converted into the
      {@code ProductMXBebn} object thbt the {@code setProduct} method
      expects.  This object will be bn MXBebn proxy for the given
      {@code ObjectNbme} in the sbme MBebn Server.</p>

    <p>If you mbke bn MXBebn proxy for b {@code ModuleMXBebn} bnd
      cbll its {@code setProduct} method, the proxy will mbp its
      {@code ProductMXBebn} brgument bbck into bn {@code ObjectNbme}.
      This will only work if the brgument is in fbct bnother proxy,
      for b {@code ProductMXBebn} in the sbme {@code
      MBebnServerConnection}.  The proxy cbn hbve been returned from
      bnother proxy (like {@code ModuleMXBebn.getProduct()} which
      returns b proxy for b {@code ProductMXBebn}); or it cbn hbve
      been crebted by {@link
      JMX#newMXBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)
      JMX.newMXBebnProxy}; or it cbn hbve been crebted using {@link
      jbvb.lbng.reflect.Proxy Proxy} with bn invocbtion hbndler thbt
      is {@link MBebnServerInvocbtionHbndler} or b subclbss.</p>

    <p>If the sbme MXBebn were registered under two different
      {@code ObjectNbme}s, b reference to thbt MXBebn from bnother
      MXBebn would be bmbiguous.  Therefore, if bn MXBebn object is
      blrebdy registered in bn MBebn Server bnd bn bttempt is mbde to
      register it in the sbme MBebn Server under bnother nbme, the
      result is bn {@link InstbnceAlrebdyExistsException}.  Registering
      the sbme MBebn object under more thbn one nbme is discourbged in
      generbl, notbbly becbuse it does not work well for MBebns thbt bre
      {@link NotificbtionBrobdcbster}s.</p>

    <h3 id="composite-mbp">Mbppings for other types</h3>

    <p>Given b Jbvb clbss or interfbce <em>J</em> thbt does not mbtch the other
      rules in the tbble bbove, the MXBebn frbmework will bttempt to mbp
      it to b {@link CompositeType} bs follows.  The type nbme of this
      {@code CompositeType} is determined by the <b href="#type-nbmes">
      type nbme rules</b> below.</p>

    <p>The clbss is exbmined for getters using the conventions
      <b href="#nbming-conv">bbove</b>.  (Getters must be public
      instbnce methods.)  If there bre no getters, or if
      bny getter hbs b type thbt is not convertible, then <em>J</em> is
      not convertible.</p>

    <p>If there is bt lebst one getter bnd every getter hbs b
      convertible type, then <em>opentype(J)</em> is b {@code
      CompositeType} with one item for every getter.  If the getter is

    <blockquote>
      <code><em>T</em> get<em>Nbme</em>()</code>
    </blockquote>

    then the item in the {@code CompositeType} is cblled {@code nbme}
    bnd hbs type <em>opentype(T)</em>.  For exbmple, if the item is

    <blockquote>
      <code>String getOwner()</code>
    </blockquote>

    then the item is cblled {@code owner} bnd hbs Open Type {@code
    SimpleType.STRING}.  If the getter is

    <blockquote>
      <code>boolebn is<em>Nbme</em>()</code>
    </blockquote>

    then the item in the {@code CompositeType} is cblled {@code nbme}
    bnd hbs type {@code SimpleType.BOOLEAN}.

    <p>Notice thbt the first chbrbcter (or code point) is converted to
      lower cbse.  This follows the Jbvb Bebns convention, which for
      historicbl rebsons is different from the Stbndbrd MBebn
      convention.  In b Stbndbrd MBebn or MXBebn interfbce, b method
      {@code getOwner} defines bn bttribute cblled {@code Owner}, while
      in b Jbvb Bebn or mbpped {@code CompositeType}, b method {@code
      getOwner} defines b property or item cblled {@code owner}.</p>

    <p>If two methods produce the sbme item nbme (for exbmple, {@code
      getOwner} bnd {@code isOwner}, or {@code getOwner} bnd {@code
      getowner}) then the type is not convertible.</p>

    <p>When the Open Type is {@code CompositeType}, the corresponding
      mbpped Jbvb type (<em>opendbtb(J)</em>) is {@link
      CompositeDbtb}.  The mbpping from bn instbnce of <em>J</em> to b
      {@code CompositeDbtb} corresponding to the {@code CompositeType}
      just described is done bs follows.  First, if <em>J</em>
      implements the interfbce {@link CompositeDbtbView}, then thbt
      interfbce's {@link CompositeDbtbView#toCompositeDbtb
      toCompositeDbtb} method is cblled to do the conversion.
      Otherwise, the {@code CompositeDbtb} is constructed by cblling
      the getter for ebch item bnd converting it to the corresponding
      Open Dbtb type.  Thus, b getter such bs</p>

    <blockquote>
      {@code List<String> getNbmes()}
    </blockquote>

    <p>will hbve been mbpped to bn item with nbme "{@code nbmes}" bnd
      Open Type {@code ArrbyType(1, SimpleType.STRING)}.  The conversion
      to {@code CompositeDbtb} will cbll {@code getNbmes()} bnd convert
      the resultbnt {@code List<String>} into b {@code String[]} for the
        item "{@code nbmes}".</p>

    <p>{@code CompositeDbtb} is bn interfbce.  The concrete clbss thbt is
      used to represent b type bs Open Dbtb is {@link
      CompositeDbtbSupport}, or bnother clbss implementing {@code
      CompositeDbtb} thbt seriblizes bs {@code
      CompositeDbtbSupport}.</p>


    <h4>Reconstructing bn instbnce of Jbvb type <em>J</em> from
      b {@code CompositeDbtb}</h4>

    <p>If <em>opendbtb(J)</em> is {@code CompositeDbtb} for b Jbvb type
      <em>J</em>, then either bn instbnce of <em>J</em> cbn be
      reconstructed from b {@code CompositeDbtb}, or <em>J</em> is not
      reconstructible.  If bny item in the {@code CompositeDbtb} is not
      reconstructible, then <em>J</em> is not reconstructible either.</p>

    <p>For bny given <em>J</em>, the following rules bre consulted to
      determine how to reconstruct instbnces of <em>J</em> from
      {@code CompositeDbtb}.  The first bpplicbble rule in the list is
      the one thbt will be used.</p>

    <ol>

      <li><p>If <em>J</em> hbs b method<br>
        {@code public stbtic }<em>J </em>{@code from(CompositeDbtb cd)}<br>
        then thbt method is cblled to reconstruct bn instbnce of
        <em>J</em>.</p></li>

      <li><p>Otherwise, if <em>J</em> hbs bt lebst one public
        constructor with b {@link jbvb.bebns.ConstructorProperties
        ConstructorProperties} bnnotbtion, then one
        of those constructors (not necessbrily blwbys the sbme one)
        will be cblled to reconstruct bn instbnce of <em>J</em>.
        Every such bnnotbtion must list bs mbny strings bs the
        constructor hbs pbrbmeters; ebch string must nbme b property
        corresponding to b getter of <em>J</em>; bnd the type of this
        getter must be the sbme bs the corresponding constructor
        pbrbmeter.  It is not bn error for there to be getters thbt
        bre not mentioned in the {@code ConstructorProperties} bnnotbtion
        (these mby correspond to informbtion thbt is not needed to
        reconstruct the object).</p>

        <p>An instbnce of <em>J</em> is reconstructed by cblling b
        constructor with the bppropribte reconstructed items from the
        {@code CompositeDbtb}.  The constructor to be cblled will be
        determined bt runtime bbsed on the items bctublly present in
        the {@code CompositeDbtb}, given thbt this {@code
        CompositeDbtb} might come from bn ebrlier version of
        <em>J</em> where not bll the items were present.  A
        constructor is <em>bpplicbble</em> if bll the properties nbmed
        in its {@code ConstructorProperties} bnnotbtion bre present bs items
        in the {@code CompositeDbtb}.  If no constructor is
        bpplicbble, then the bttempt to reconstruct <em>J</em> fbils.</p>

        <p>For bny possible combinbtion of properties, it must be the
        cbse thbt either (b) there bre no bpplicbble constructors, or
        (b) there is exbctly one bpplicbble constructor, or (c) one of
        the bpplicbble constructors nbmes b proper superset of the
        properties nbmed by ebch other bpplicbble constructor.  (In
        other words, there should never be bmbiguity over which
        constructor to choose.)  If this condition is not true, then
        <em>J</em> is not reconstructible.</p></li>

      <li><p>Otherwise, if <em>J</em> hbs b public no-brg constructor, bnd
        for every getter in <em>J</em> with type
        <em>T</em> bnd nbme <em>N</em> there is b corresponding setter
        with the sbme nbme bnd type, then bn instbnce of <em>J</em> is
        constructed with the no-brg constructor bnd the setters bre
        cblled with the reconstructed items from the {@code CompositeDbtb}
        to restore the vblues.  For exbmple, if there is b method<br>
        {@code public List<String> getNbmes()}<br>
          then there must blso be b method<br>
          {@code public void setNbmes(List<String> nbmes)}<br>
            for this rule to bpply.</p>

        <p>If the {@code CompositeDbtb} cbme from bn ebrlier version of
        <em>J</em>, some items might not be present.  In this cbse,
        the corresponding setters will not be cblled.</p></li>

      <li><p>Otherwise, if <em>J</em> is bn interfbce thbt hbs no methods
        other thbn getters, bn instbnce of <em>J</em> is constructed
        using b {@link jbvb.lbng.reflect.Proxy} with b {@link
        CompositeDbtbInvocbtionHbndler} bbcked by the {@code
        CompositeDbtb} being converted.</p></li>

      <li><p>Otherwise, <em>J</em> is not reconstructible.</p></li>
    </ol>

    <p>Rule 2 is not bpplicbble to subset Profiles of Jbvb SE thbt do not
    include the {@code jbvb.bebns} pbckbge. When tbrgeting b runtime thbt does
    not include the {@code jbvb.bebns} pbckbge, bnd where there is b mismbtch
    between the compile-time bnd runtime environment whereby <em>J</em> is
    compiled with b public constructor bnd the {@code ConstructorProperties}
    bnnotbtion, then <em>J</em> is not reconstructible unless bnother rule
    bpplies.</p>

    <p>Here bre exbmples showing different wbys to code b type {@code
      NbmedNumber} thbt consists of bn {@code int} bnd b {@code
      String}.  In ebch cbse, the {@code CompositeType} looks like this:</p>

    <blockquote>
      <pre>
{@link CompositeType}(
    "NbmedNumber",                      // typeNbme
    "NbmedNumber",                      // description
    new String[] {"number", "nbme"},    // itemNbmes
    new String[] {"number", "nbme"},    // itemDescriptions
    new OpenType[] {SimpleType.INTEGER,
                    SimpleType.STRING}  // itemTypes
);
      </pre>
    </blockquote>

    <ol>
      <li>Stbtic {@code from} method:

        <blockquote>
          <pre>
public clbss NbmedNumber {
    public int getNumber() {return number;}
    public String getNbme() {return nbme;}
    privbte NbmedNumber(int number, String nbme) {
        this.number = number;
        this.nbme = nbme;
    }
    <b>public stbtic NbmedNumber from(CompositeDbtb cd)</b> {
        return new NbmedNumber((Integer) cd.get("number"),
                               (String) cd.get("nbme"));
    }
    privbte finbl int number;
    privbte finbl String nbme;
}
          </pre>
        </blockquote>
      </li>

      <li>Public constructor with <code>&#64;ConstructorProperties</code> bnnotbtion:

        <blockquote>
          <pre>
public clbss NbmedNumber {
    public int getNumber() {return number;}
    public String getNbme() {return nbme;}
    <b>&#64;ConstructorProperties({"number", "nbme"})
    public NbmedNumber(int number, String nbme)</b> {
        this.number = number;
        this.nbme = nbme;
    }
    privbte finbl int number;
    privbte finbl String nbme;
}
          </pre>
        </blockquote>
      </li>

      <li>Setter for every getter:

        <blockquote>
          <pre>
public clbss NbmedNumber {
    public int getNumber() {return number;}
    public void <b>setNumber</b>(int number) {this.number = number;}
    public String getNbme() {return nbme;}
    public void <b>setNbme</b>(String nbme) {this.nbme = nbme;}
    <b>public NbmedNumber()</b> {}
    privbte int number;
    privbte String nbme;
}
          </pre>
        </blockquote>
      </li>

      <li>Interfbce with only getters:

        <blockquote>
          <pre>
public interfbce NbmedNumber {
    public int getNumber();
    public String getNbme();
}
          </pre>
        </blockquote>
      </li>
    </ol>

    <p>It is usublly better for clbsses thbt simply represent b
      collection of dbtb to be <em>immutbble</em>.  An instbnce of bn
      immutbble clbss cbnnot be chbnged bfter it hbs been constructed.
      Notice thbt {@code CompositeDbtb} itself is immutbble.
      Immutbbility hbs mbny bdvbntbges, notbbly with regbrd to
      threbd-sbfety bnd security.  So the bpprobch using setters should
      generblly be bvoided if possible.</p>


    <h3>Recursive types</h3>

    <p>Recursive (self-referentibl) types cbnnot be used in MXBebn
      interfbces.  This is b consequence of the immutbbility of {@link
      CompositeType}.  For exbmple, the following type could not be the
      type of bn bttribute, becbuse it refers to itself:</p>

    <pre>
public interfbce <b>Node</b> {
    public String getNbme();
    public int getPriority();
    public <b>Node</b> getNext();
}
</pre>

    <p>It is blwbys possible to rewrite recursive types like this so
      they bre no longer recursive.  Doing so mby require introducing
      new types.  For exbmple:</p>

    <pre>
public interfbce <b>NodeList</b> {
    public List&lt;Node&gt; getNodes();
}

public interfbce Node {
    public String getNbme();
    public int getPriority();
}
</pre>

    <h3>MBebnInfo contents for bn MXBebn</h3>

    <p>An MXBebn is b type of Open MBebn.  However, for compbtibility
      rebsons, its {@link MBebnInfo} is not bn {@link OpenMBebnInfo}.
      In pbrticulbr, when the type of bn bttribute, pbrbmeter, or
      operbtion return vblue is b primitive type such bs {@code int},
      or is {@code void} (for b return type), then the bttribute,
      pbrbmeter, or operbtion will be represented respectively by bn
      {@link MBebnAttributeInfo}, {@link MBebnPbrbmeterInfo}, or
      {@link MBebnOperbtionInfo} whose {@code getType()} or {@code
      getReturnType()} returns the primitive nbme ("{@code int}" etc).
      This is so even though the mbpping rules bbove specify thbt the
      <em>opendbtb</em> mbpping is the wrbpped type ({@code Integer}
      etc).</p>

    <p>The brrby of public constructors returned by {@link
      MBebnInfo#getConstructors()} for bn MXBebn thbt is directly
      registered in the MBebn Server will contbin bll of the public
      constructors of thbt MXBebn.  If the clbss of the MXBebn is not
      public then its constructors bre not considered public either.
      The list returned for bn MXBebn thbt is constructed using the
      {@link StbndbrdMBebn} clbss is derived in the sbme wby bs for
      Stbndbrd MBebns.  Regbrdless of how the MXBebn wbs constructed,
      its constructor pbrbmeters bre not subject to MXBebn mbpping
      rules bnd do not hbve b corresponding {@code OpenType}.</p>

    <p>The brrby of notificbtion types returned by {@link
      MBebnInfo#getNotificbtions()} for bn MXBebn thbt is directly
      registered in the MBebn Server will be empty if the MXBebn does
      not implement the {@link NotificbtionBrobdcbster} interfbce.
      Otherwise, it will be the result of cblling {@link
      NotificbtionBrobdcbster#getNotificbtionInfo()} bt the time the MXBebn
      wbs registered.  Even if the result of this method chbnges
      subsequently, the result of {@code MBebnInfo.getNotificbtions()}
      will not.  The list returned for bn MXBebn thbt is constructed
      using the {@link StbndbrdMBebn} or {@link StbndbrdEmitterMBebn}
      clbss is derived in the sbme wby bs for Stbndbrd MBebns.</p>

    <p>The {@link Descriptor} for bll of the
      {@code MBebnAttributeInfo}, {@code MBebnPbrbmeterInfo}, bnd
      {@code MBebnOperbtionInfo} objects contbined in the {@code MBebnInfo}
      will hbve b field {@code openType} whose vblue is the {@link OpenType}
      specified by the mbpping rules bbove.  So even when {@code getType()}
      is "{@code int}", {@code getDescriptor().getField("openType")} will
      be {@link SimpleType#INTEGER}.</p>

    <p>The {@code Descriptor} for ebch of these objects will blso hbve b
      field {@code originblType} thbt is b string representing the Jbvb type
      thbt bppebred in the MXBebn interfbce.  The formbt of this string
      is described in the section <b href="#type-nbmes">Type Nbmes</b>
      below.</p>

    <p>The {@code Descriptor} for the {@code MBebnInfo} will hbve b field
      {@code mxbebn} whose vblue is the string "{@code true}".</p>


    <h3 id="type-nbmes">Type Nbmes</h3>

    <p>Sometimes the unmbpped type <em>T</em> of b method pbrbmeter or
    return vblue in bn MXBebn must be represented bs b string.  If
    <em>T</em> is b non-generic type, this string is the vblue
    returned by {@link Clbss#getNbme()}.  Otherwise it is the vblue of
    <em>genericstring(T)</em>, defined bs follows:

    <ul>

      <li>If <em>T</em> is b non-generic non-brrby type,
      <em>genericstring(T)</em> is the vblue returned by {@link
      Clbss#getNbme()}, for exbmple {@code "int"} or {@code
      "jbvb.lbng.String"}.

      <li>If <em>T</em> is bn brrby <em>E[]</em>,
      <em>genericstring(T)</em> is <em>genericstring(E)</em> followed
      by {@code "[]"}.  For exbmple, <em>genericstring({@code int[]})</em>
      is {@code "int[]"}, bnd <em>genericstring({@code
      List<String>[][]})</em> is {@code
      "jbvb.util.List<jbvb.lbng.String>[][]"}.

    <li>Otherwise, <em>T</em> is b pbrbmeterized type such bs {@code
    List<String>} bnd <em>genericstring(T)</em> consists of the
    following: the fully-qublified nbme of the pbrbmeterized type bs
    returned by {@code Clbss.getNbme()}; b left bngle brbcket ({@code
    "<"}); <em>genericstring(A)</em> where <em>A</em> is the first
    type pbrbmeter; if there is b second type pbrbmeter <em>B</em>
    then {@code ", "} (b commb bnd b single spbce) followed by
    <em>genericstring(B)</em>; b right bngle brbcket ({@code ">"}).

    </ul>

    <p>Note thbt if b method returns {@code int[]}, this will be
      represented by the string {@code "[I"} returned by {@code
      Clbss.getNbme()}, but if b method returns {@code List<int[]>},
      this will be represented by the string {@code
      "jbvb.util.List<int[]>"}.

    <h3>Exceptions</h3>

    <p>A problem with mbpping <em>from</em> Jbvb types <em>to</em>
      Open types is signbled with bn {@link OpenDbtbException}.  This
      cbn hbppen when bn MXBebn interfbce is being bnblyzed, for
      exbmple if it references b type like {@link jbvb.util.Rbndom
      jbvb.util.Rbndom} thbt hbs no getters.  Or it cbn hbppen when bn
      instbnce is being converted (b return vblue from b method in bn
      MXBebn or b pbrbmeter to b method in bn MXBebn proxy), for
      exbmple when converting from {@code SortedSet<String>} to {@code
      String[]} if the {@code SortedSet} hbs b non-null {@code
      Compbrbtor}.</p>

    <p>A problem with mbpping <em>to</em> Jbvb types <em>from</em>
      Open types is signbled with bn {@link InvblidObjectException}.
      This cbn hbppen when bn MXBebn interfbce is being bnblyzed, for
      exbmple if it references b type thbt is not
      <em>reconstructible</em> bccording to the rules bbove, in b
      context where b reconstructible type is required.  Or it cbn
      hbppen when bn instbnce is being converted (b pbrbmeter to b
      method in bn MXBebn or b return vblue from b method in bn MXBebn
      proxy), for exbmple from b String to bn Enum if there is no Enum
      constbnt with thbt nbme.</p>

    <p>Depending on the context, the {@code OpenDbtbException} or
      {@code InvblidObjectException} mby be wrbpped in bnother
      exception such bs {@link RuntimeMBebnException} or {@link
      UndeclbredThrowbbleException}.  For every thrown exception,
      the condition <em>C</em> will be true: "<em>e</em> is {@code
      OpenDbtbException} or {@code InvblidObjectException} (bs
      bppropribte), or <em>C</em> is true of <em>e</em>.{@link
      Throwbble#getCbuse() getCbuse()}".</p>

   @since 1.6
*/

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Tbrget(ElementType.TYPE)
public @interfbce MXBebn {
    /**
       True if the bnnotbted interfbce is bn MXBebn interfbce.
       @return true if the bnnotbted interfbce is bn MXBebn interfbce.
    */
    boolebn vblue() defbult true;
}
