import React, { useEffect } from 'react';
import { connect } from "react-redux";

import { useParams } from 'react-router';
import { useNavigate } from 'react-router-dom';
import { MdPerson } from "react-icons/md";

import { Center, Box, VStack, Button, Textarea, HStack, Spacer, FormControl, Flex } from "@chakra-ui/react"

import { Formik, Form, Field, ErrorMessage, FieldArray } from 'formik';

import { fetchInterviewId } from "../redux/indexAction.js"

function CardNews({ fetchInterviewId, loading, interviewId, interviewUser }){
    const params = useParams();
    const navigator = useNavigate();

    const linkToAnswerEvent = () => {
        navigator(`/interviews/${params.interviewId}/answer`);
    }

    useEffect(() => fetchInterviewId(params.interviewId),[])

    return (
        <div>
            <Center>
                <VStack w = "70%" spacing = {4}>
                    
                    <Box w = "100%" h = "100px" color = "black" fontWeight = "bold"
                        bgGradient = "linear(to-r, #E6F0FF, #5078E7)"
                        alignItems = "center" pl = "2%" pt = "20px"
                    >   
                        <Box display = "flex">
                            <MdPerson size = "1.5em"/>
                            <Box pl = "10px">{interviewUser && interviewUser.nickname}</Box>
                        </Box>
                        <Box mt = "10px">{interviewId.question}</Box>
                        
                    </Box> 
                    <Box w= "100%">
                    <Formik 
                        initialValues={{
                            preAnswer : ""
                        }}
                        onSubmit={async (values) => {
                            await new Promise((r) => setTimeout(r, 500));
                            alert(JSON.stringify(values, null, 2));
                        }}
                    >
                        <Form>
                        <Field 
                            name = "preAnswer"
                            render={({ field, form: { isSubmitting } }) => (
                                <Textarea {...field} h = "300px" size='lg'
                                    placeholder='????????? ????????? ??????????????????? ????????? ????????? ???????????????' />
                            )}
                        />
                        
                        <Flex w = "100%">
                        
                        <Spacer />

                        <HStack spacing = {4} mt = {5}>
                        <Button 
                            type = "button"
                            w = "200px" variant = "primary"
                            onClick = {linkToAnswerEvent}
                        >
                            ?????? ??????
                        </Button>
                        <Button 
                            type = "submit"
                            w = "200px" variant = "primary"
                        >
                            ??? ?????? ????????????
                        </Button>
                        </HStack>
                        </Flex>
                        </Form>
                    </Formik>    
                    </Box>

                    
                </VStack>
            </Center>
        </div>
    )
}

const mapStateToProps = ({ interviewId }) => {
    return {
        interviewId : interviewId.items,
        interviewUser : interviewId.items.user,
        
    }
}

const mapDispatchToProps = {
    fetchInterviewId    
}

export default connect(mapStateToProps, mapDispatchToProps)(CardNews);