
package br.sceweb.teste;


import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	
	static EmpresaDAO empresaDAO;
	static Empresa empresa;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	}

	@Test
	public void CT01UC01FBCadastra_empresa_com_sucesso() {
		empresaDAO.exclui("89424232000180");
		assertEquals(1,empresaDAO.adiciona(empresa));
		empresaDAO.exclui("89424232000180");
	}
	
	@Test(expected = RuntimeException.class)
	public void CT02UC01A2Cadastra_empresa_cnpj_ja_cadastrado(){
		empresaDAO.adiciona(empresa);
		assertEquals(0, empresaDAO.adiciona(empresa));
	}
	
	@Test
	public void CT03UC01A3Cadastra_empresa_cnpj_invalido(){
		Empresa empresa2 = new Empresa();
		try{
			empresa2.setCnpj("01--");
			fail("deveria disparar uma exception");
		} catch (Exception e){
			assertEquals("CNPJ inválido!", e.getMessage());
		}
	}
	
	@Test
	public void CT04UC01A4Cadastra_empresa_com_dados_invalidos(){
		Empresa empresa2 = new Empresa();
		try{
			empresa2.setNomeDaEmpresa("");
			fail("deveria disparar uma exception");
		} catch (Exception e){
			assertEquals("nome da empresa inválido!", e.getMessage());
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("89424232000180");
	}



}
